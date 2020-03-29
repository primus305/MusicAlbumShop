/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain.dto;

import lombok.Data;

/**
 *
 * @author rancha
 */
@Data
public class PorudzbinaDTO {

    private Long id;
    private String drzava;
    private String grad;
    private String adresa;
    private Long postanskiBroj;
    private boolean payed;
    private String datumPorudzbine;
    private String datumDostave;
    private PotrosackaKorpaDTO potrosackaKorpa;
    private KorisnikDTO korisnik;

    public PorudzbinaDTO(String drzava, String grad, String adresa, Long postanskiBroj, boolean payed, String datumPorudzbine, String datumDostave, PotrosackaKorpaDTO potrosackaKorpa, KorisnikDTO korisnik) {
        this.drzava = drzava;
        this.grad = grad;
        this.adresa = adresa;
        this.postanskiBroj = postanskiBroj;
        this.payed = payed;
        this.datumPorudzbine = datumPorudzbine;
        this.datumDostave = datumDostave;
        this.potrosackaKorpa = potrosackaKorpa;
        this.korisnik = korisnik;
    }

    public PorudzbinaDTO() {
    }

    
}
