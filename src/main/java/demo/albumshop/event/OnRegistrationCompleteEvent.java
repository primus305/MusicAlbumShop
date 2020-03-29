/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.event;

import demo.albumshop.domain.Korisnik;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author rancha
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent{
    private String appUrl;
    private Locale locale;
    private Korisnik korisnik;
 
    public OnRegistrationCompleteEvent(
      Korisnik korisnik, Locale locale, String appUrl) {
        super(korisnik);
         
        this.korisnik = korisnik;
        this.locale = locale;
        this.appUrl = appUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
