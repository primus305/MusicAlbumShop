/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author rancha
 */
@Entity
@IdClass(StavkaPK.class)
public class StavkaKorpe implements Serializable {

    @Id
    private Long redniBroj;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "potrosackaKorpa_id")
    private PotrosackaKorpa potrosackaKorpa;
    @ManyToOne
    private Album album;
    private BigDecimal cena;
    private BigDecimal ukupnaCena;
    private int quantity;

    public StavkaKorpe() {
    }

    public StavkaKorpe(Long redniBroj, Album album, BigDecimal cena, BigDecimal ukupnaCena, int quantity) {
        this.redniBroj = redniBroj;
        this.album = album;
        this.cena = cena;
        this.ukupnaCena = ukupnaCena;
        this.quantity = quantity;
    }

    public PotrosackaKorpa getPotrosackaKorpa() {
        return potrosackaKorpa;
    }

    public void setPotrosackaKorpa(PotrosackaKorpa potrosackaKorpa) {
        this.potrosackaKorpa = potrosackaKorpa;
    }

    public Long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Long redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public BigDecimal getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(BigDecimal ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        final StavkaKorpe other = (StavkaKorpe) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.redniBroj, other.redniBroj)) {
            return false;
        }
        if (!Objects.equals(this.potrosackaKorpa, other.potrosackaKorpa)) {
            return false;
        }
        if (!Objects.equals(this.album, other.album)) {
            return false;
        }
        if (!Objects.equals(this.cena, other.cena)) {
            return false;
        }
        if (!Objects.equals(this.ukupnaCena, other.ukupnaCena)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StavkaKorpe{" + "redniBroj=" + redniBroj + ", potrosackaKorpa=" + potrosackaKorpa + ", album=" + album + ", cena=" + cena + ", ukupnaCena=" + ukupnaCena + ", quantity=" + quantity + '}';
    }

   

}
