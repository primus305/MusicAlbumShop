/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.controller;

import demo.albumshop.event.OnRegistrationCompleteEvent;
import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.Uloga;
import demo.albumshop.domain.Valuta;
import demo.albumshop.domain.VerificationToken;
import demo.albumshop.domain.Zanr;
import demo.albumshop.domain.dto.KorisnikDTO;
import demo.albumshop.mapper.KorisnikMapper;
import demo.albumshop.service.KorisnikService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rancha
 */
@Controller
public class UserController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikMapper korisnikMapper;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpSession session) {
        session.setAttribute("valuta", Valuta.RSD);
        List<String> zanroviString = new ArrayList<>();
        for (Zanr z : Zanr.values()) {
            String zanr = z.name();
            if (zanr.contains("_")) {
                String replace = zanr.replace("_", "/");
                zanroviString.add(replace);
            } else {
                zanroviString.add(zanr);
            }
        }
        session.setAttribute("zanrovi", zanroviString);
        return "index";
    }

    @GetMapping({"/home", "/"})
    public String home(HttpSession session) {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/successRegister")
    public String successRegister() {
        return "successRegister";
    }
    
    @GetMapping("/registerConfirmation")
    public String registerConfirmation() {
        return "registerConfirmation";
    }
    
    @GetMapping("/badUser")
    public String badUser() {
        return "badUser";
    }

    @ModelAttribute("registrationForm")
    public KorisnikDTO regForm() {
        return new KorisnikDTO(Uloga.OBICAN_KORISNIK);
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registerForm() {
        return "registration";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("registrationForm") KorisnikDTO korisnikDTO,
            BindingResult result,
            WebRequest request,
            Errors errors) {
        if (result.hasErrors()) {
            return "registration";
        }
        Korisnik registered = korisnikService.save(korisnikDTO);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            System.out.println(me.getMessage());
            return "redirect:/registration";
        }
        return "redirect:/registerConfirmation?msgRegConfirmation=Sistem vam je poslao e-mail sa potvrdom registracije.";
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = korisnikService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/badUser?neuspesnaRegistracija=Neuspesna registracija";
        }

        KorisnikDTO user = korisnikMapper.korisnikToKorisnikDTO(verificationToken.getKorisnik());
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/badUser?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        korisnikService.save(user);
        return "redirect:/successRegister?uspesnaRegistracija=Uspesna registracija.";
    }
    
    @GetMapping("/admin/allUsers")
    public ModelAndView allUsers() {
        List<KorisnikDTO> korisnici = korisnikService.findAll();
        korisnici.remove(0);

        ModelAndView modelAndView = new ModelAndView("/admin/allUsers");
        modelAndView.addObject("users", korisnici);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model m) {
        KorisnikDTO korisnikDTO = korisnikService.findById(id);
        if (korisnikDTO.getUloga() == Uloga.OBICAN_KORISNIK) {
            korisnikDTO.setUloga(Uloga.ADMINISTRATOR);
        } else {
            korisnikDTO.setUloga(Uloga.OBICAN_KORISNIK);
        }
        korisnikService.save(korisnikDTO);
        m.addAttribute("korisnikPromena", "Sistem je izmenio korisnika.");
        return "redirect:/admin/allUsers";
    }
    
}
