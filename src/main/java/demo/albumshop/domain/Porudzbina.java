/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author rancha
 */
@Entity
public class Porudzbina implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String drzava;
    private String grad;
    private String adresa;
    private Long postanskiBroj;
    private boolean payed;
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date datumPorudzbine;
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date datumDostave;
    @OneToOne
    private PotrosackaKorpa potrosackaKorpa;
    @ManyToOne
    private Korisnik korisnik;

    public Porudzbina() {
    }

    public Porudzbina(String drzava, String grad, String adresa, Long postanskiBroj, boolean payed, Date datumPorudzbine, Date datumDostave, PotrosackaKorpa potrosackaKorpa, Korisnik korisnik) {
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

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatumPorudzbine() {
        return datumPorudzbine;
    }

    public void setDatumPorudzbine(Date datumPorudzbine) {
        this.datumPorudzbine = datumPorudzbine;
    }

    public Date getDatumDostave() {
        return datumDostave;
    }

    public void setDatumDostave(Date datumDostave) {
        this.datumDostave = datumDostave;
    }

    public PotrosackaKorpa getPotrosackaKorpa() {
        return potrosackaKorpa;
    }

    public void setPotrosackaKorpa(PotrosackaKorpa potrosackaKorpa) {
        this.potrosackaKorpa = potrosackaKorpa;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Long getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(Long postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Porudzbina other = (Porudzbina) obj;
        if (this.payed != other.payed) {
            return false;
        }
        if (!Objects.equals(this.drzava, other.drzava)) {
            return false;
        }
        if (!Objects.equals(this.grad, other.grad)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.postanskiBroj, other.postanskiBroj)) {
            return false;
        }
        if (!Objects.equals(this.datumPorudzbine, other.datumPorudzbine)) {
            return false;
        }
        if (!Objects.equals(this.datumDostave, other.datumDostave)) {
            return false;
        }
        if (!Objects.equals(this.potrosackaKorpa, other.potrosackaKorpa)) {
            return false;
        }
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Porudzbina{" + "id=" + id + ", drzava=" + drzava + ", grad=" + grad + ", adresa=" + adresa + ", postanskiBroj=" + postanskiBroj + ", payed=" + payed + ", datumPorudzbine=" + datumPorudzbine + ", datumDostave=" + datumDostave + ", potrosackaKorpa=" + potrosackaKorpa + ", korisnik=" + korisnik + '}';
    }

    
    
}
