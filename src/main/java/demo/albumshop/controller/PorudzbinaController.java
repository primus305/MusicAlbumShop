/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.controller;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.Valuta;
import demo.albumshop.domain.dto.KorisnikDTO;
import demo.albumshop.domain.dto.PorudzbinaDTO;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import demo.albumshop.domain.dto.StavkaKorpeDTO;
import demo.albumshop.konvertor.Konvertor;
import demo.albumshop.paypal.PayPalResult;
import demo.albumshop.paypal.PayPalSucess;
import demo.albumshop.service.PayPalService;
import demo.albumshop.service.PorudzbinaService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = "/porudzbina/*")
public class PorudzbinaController {

    @Autowired
    private PorudzbinaService porudzbinaService;
    @Autowired
    private PayPalService payPalService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String nova() {
        return "/porudzbina/add";
    }

    @ModelAttribute(name = "porudzbina")
    public PorudzbinaDTO porudzbina(HttpSession session) {
        KorisnikDTO korisnik = (KorisnikDTO) session.getAttribute("currentUser");
        PotrosackaKorpaDTO pk = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        c.add(Calendar.DATE, 7);
        Date datumDostave = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return new PorudzbinaDTO("", "", "", 0l, false, sdf.format(new Date()), sdf.format(datumDostave), pk, korisnik);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute(name = "porudzbina") @Valid PorudzbinaDTO porudzbinaDTO,
            BindingResult bindingResult, HttpSession session,
            @AuthenticationPrincipal Korisnik korisnik, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "/porudzbina/add";
        }
        porudzbinaDTO = porudzbinaService.save(porudzbinaDTO, korisnik);
        session.setAttribute("order", porudzbinaDTO);

        modelMap.put("payPalConfig", payPalService.getPayPalConfig());
        return "/porudzbina/add";
    }

    @RequestMapping(value = "placanje")
    public String placanje(ModelMap modelMap) {
        modelMap.put("payPalConfig", payPalService.getPayPalConfig());
        return "/porudzbina/placanje";
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(HttpServletRequest request, HttpSession session, Model m) {
        PorudzbinaDTO pDTO = (PorudzbinaDTO) session.getAttribute("order");
        PayPalSucess payPalSucess = new PayPalSucess();
        PayPalResult payPalResult = payPalSucess.getPayPal(request, payPalService.getPayPalConfig());
        if (payPalResult.getPayer_email() != null && payPalResult.getPayer_email().equals(pDTO.getKorisnik().getEmail())) {
            pDTO.setPayed(true);
            pDTO = porudzbinaService.update(pDTO);
        }
        session.setAttribute("order", null);
        session.setAttribute("shoppingCart", null);
        session.setAttribute("valuta", Valuta.RSD);
        m.addAttribute("orderSuccess", pDTO);
        m.addAttribute("orderSuccessMsg", "Sistem je zapamtio vasu porudzbinu.");
        
        return "/porudzbina/success";
    }

    @RequestMapping(value = "all/{page}", method = RequestMethod.GET)
    public ModelAndView allOrders(@AuthenticationPrincipal Korisnik korisnik, @PathVariable("page") int page) {
        int limit = 8;
        List<PorudzbinaDTO> orders = porudzbinaService.findAll(korisnik);
        List<PorudzbinaDTO> porudzbinePage = new ArrayList<>();

        double totalPages = Math.ceil((double) orders.size() / (double) limit);
        List<Long> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(new Long(i));
        }
        int offset = limit * (page - 1);
        int granica = offset + limit;
        if (granica > orders.size()) {
            granica = orders.size();
        }
        for (int i = offset; i < granica; i++) {
            porudzbinePage.add(orders.get(i));
        }

        ModelAndView modelAndView = new ModelAndView("/porudzbina/all");
        modelAndView.addObject("orders", porudzbinePage);
        modelAndView.addObject("pageNumbers", pageNumbers);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping(value = "gotovo", method = RequestMethod.POST)
    public String gotovo(HttpSession session) {
        session.setAttribute("order", null);
        session.setAttribute("shoppingCart", null);
        session.setAttribute("valuta", Valuta.RSD);
        return "redirect:/album/all/1";
    }

    @RequestMapping(value = "restapi", method = RequestMethod.GET)
    public String rest(HttpSession session, @RequestParam(name = "valuta") String valuta) {
        PotrosackaKorpaDTO pkDTO = (PotrosackaKorpaDTO) session.getAttribute("shoppingCart");
        Konvertor konvertor = new Konvertor();

        for (StavkaKorpeDTO stavka : pkDTO.getStavkeDTOs()) {
            String konRez = konvertor.getRezultat(pkDTO.getValuta().toString(), valuta, stavka.getCena());
            BigDecimal novaCena = new BigDecimal(konRez);
            novaCena = novaCena.setScale(2, BigDecimal.ROUND_HALF_EVEN);

            stavka.setCena(novaCena);
            BigDecimal ukupnaCena = novaCena.add(novaCena.multiply(new BigDecimal(stavka.getAlbum().getPoreskaStopa().getIznos())));
            ukupnaCena = ukupnaCena.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            stavka.setUkupnaCena(ukupnaCena);
        }
        pkDTO.srediUkupanIznos();
        pkDTO.srediUkupanIznosPDV();
        pkDTO.setValuta(Valuta.valueOf(valuta));

        session.setAttribute("shoppingCart", pkDTO);
        session.setAttribute("valuta", Valuta.valueOf(valuta));

        return "redirect:/cart/myCart";
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/porudzbina/view");
        PorudzbinaDTO porudzbina = porudzbinaService.findById(id);
        modelAndView.addObject("viewOrder", porudzbina);
        return modelAndView;
    }
}
