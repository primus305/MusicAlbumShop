/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.controller;

import demo.albumshop.domain.Valuta;
import demo.albumshop.domain.dto.AlbumDTO;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import demo.albumshop.domain.dto.StavkaKorpeDTO;
import demo.albumshop.service.AlbumService;
import demo.albumshop.service.PotrosackaKorpaService;
import java.math.BigDecimal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rancha
 */
@Controller
@RequestMapping(value = "/cart/*")
public class PotrosackaKorpaController {

    @Autowired
    private PotrosackaKorpaService potrosackaKorpaService;

    @Autowired
    private AlbumService albumService;

    @RequestMapping(value = "addItem", method = RequestMethod.POST)
    public String save(@RequestParam(name = "albumID") Long albumID,
            @RequestParam(name = "quantity") int quantity, HttpSession session, Model m) {
        PotrosackaKorpaDTO pk;
        boolean izmena = false;
        AlbumDTO a = albumService.findById(albumID);
        if (a.getDostupnaKolicina() < quantity) {
            m.addAttribute("err", "Trenutno nemamo toliko albuma.");
        } else {
            m.addAttribute("porukaUspesnosti", a.getNaziv() + " je dodat u korpu.");
            if (session.getAttribute("shoppingCart") != null) {
                pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
            } else {
                pk = new PotrosackaKorpaDTO(Valuta.RSD, new BigDecimal(0), new BigDecimal(0));
                addCartToSession(session, pk);
            }
            for (StavkaKorpeDTO s : pk.getStavkeDTOs()) {
                if (s.getAlbum().equals(a)) {
                    izmena = true;
                    s.setQuantity(s.getQuantity() + quantity);
                }
            }
            if (!izmena) {
                Long rb = new Long(pk.getStavkeDTOs().size() + 1);
                BigDecimal ukupnaCena = a.getCena().add(a.getCena().multiply(new BigDecimal(a.getPoreskaStopa().getIznos())));
                ukupnaCena = ukupnaCena.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                StavkaKorpeDTO stavka = new StavkaKorpeDTO(rb, a, a.getCena(), ukupnaCena, quantity);
                pk.dodajStavku(stavka);
            }
            pk.srediUkupanIznos();
            pk.srediUkupanIznosPDV();
            addCartToSession(session, pk);
        }

        return "redirect:/album/all/1";
    }

    public void addCartToSession(HttpSession session, PotrosackaKorpaDTO pk) {
        session.setAttribute("shoppingCart", pk);
    }

    @RequestMapping(value = "myCart")
    public String myCart() {
        return "/cart/myCart";
    }

    @RequestMapping(value = "edit")
    public String edit() {
        return "/cart/edit";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String nova(HttpSession session) {
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        
        pk = potrosackaKorpaService.save(pk);
        for (StavkaKorpeDTO stavkaKorpe : pk.getStavkeDTOs()) {
            AlbumDTO a = albumService.findById(stavkaKorpe.getAlbum().getId());
            a.setDostupnaKolicina(a.getDostupnaKolicina() - stavkaKorpe.getQuantity());
            albumService.save(a);
        }
        addCartToSession(session, pk);
        return "redirect:/porudzbina/add";
    }

    @RequestMapping(value = "item/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id, HttpSession session) {
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        ModelAndView modelAndView = new ModelAndView("/cart/edit");
        StavkaKorpeDTO stavka = new StavkaKorpeDTO();
        for (StavkaKorpeDTO sk : pk.getStavkeDTOs()) {
            if (sk.getRedniBroj().equals(id)) {
                stavka = sk;
            }
        }
        pk.obrisiStavku(stavka);
        pk.srediUkupanIznos();
        pk.srediUkupanIznosPDV();
        addCartToSession(session, pk);
        return modelAndView;
    }

    @RequestMapping(value = "item/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateItem(@PathVariable("id") Long id, HttpSession session,
            @RequestParam(name = "quantity") int quantity) {
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        ModelAndView modelAndView = new ModelAndView("/cart/edit");
        for (StavkaKorpeDTO sk : pk.getStavkeDTOs()) {
            if (sk.getRedniBroj().equals(id)) {
                AlbumDTO a = albumService.findById(sk.getAlbum().getId());
                if (sk.getQuantity() > quantity) {
                    int razlika = sk.getQuantity() - quantity;
                    a.setDostupnaKolicina(a.getDostupnaKolicina() + razlika);
                }
                if (sk.getQuantity() < quantity) {
                    int razlika = quantity - sk.getQuantity();
                    a.setDostupnaKolicina(a.getDostupnaKolicina() - razlika);
                }
                sk.setQuantity(quantity);
                albumService.save(a);
            }
        }
        pk.srediUkupanIznos();
        pk.srediUkupanIznosPDV();
        addCartToSession(session, pk);
        return modelAndView;
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public String update(HttpSession session, Model m) {
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        potrosackaKorpaService.save(pk);
        m.addAttribute("korpaEdit", "Sistem je izmenio potrosacku korpu.");
        return "redirect:/porudzbina/add";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteStavka(@PathVariable("id") Long id, HttpSession session, Model m) {
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        StavkaKorpeDTO stavka = new StavkaKorpeDTO();
        for (StavkaKorpeDTO sk : pk.getStavkeDTOs()) {
            if (sk.getRedniBroj().equals(id)) {
                stavka = sk;
            }
        }
        pk.obrisiStavku(stavka);
        pk.srediUkupanIznos();
        pk.srediUkupanIznosPDV();
        addCartToSession(session, pk);
        m.addAttribute("albumDelete", "Sistem je obrisao album iz vase korpe.");
        return "redirect:/album/all/1";
    }
}
