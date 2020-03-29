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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author rancha
 */
@Entity
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String naziv;
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    private Date datumIzdavanja;
    @Enumerated(EnumType.STRING)
    private Zanr zanr;
    @NotNull
    private BigDecimal staraCena;
    @NotNull
    private BigDecimal cena;
    @NotNull
    private Long dostupnaKolicina;
    @NotNull
    private String slika;
    @NotNull
    private String izvodjac;
    @ManyToOne
    @JoinColumn(name = "poreskaStopa_id")
    private PoreskaStopa poreskaStopa;

    public Album(String naziv, Date datumIzdavanja, BigDecimal staraCena, BigDecimal cena, Long dostupnaKolicina, String slika, String izvodjac, PoreskaStopa poreskaStopa) {
        this.naziv = naziv;
        this.datumIzdavanja = datumIzdavanja;
        this.staraCena = staraCena;
        this.cena = cena;
        this.dostupnaKolicina = dostupnaKolicina;
        this.slika = slika;
        this.izvodjac = izvodjac;
        this.poreskaStopa = poreskaStopa;
    }

    public Album() {
    }

    public PoreskaStopa getPoreskaStopa() {
        return poreskaStopa;
    }

    public void setPoreskaStopa(PoreskaStopa poreskaStopa) {
        this.poreskaStopa = poreskaStopa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }


    public Long getDostupnaKolicina() {
        return dostupnaKolicina;
    }

    public void setDostupnaKolicina(Long dostupnaKolicina) {
        this.dostupnaKolicina = dostupnaKolicina;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getIzvodjac() {
        return izvodjac;
    }

    public void setIzvodjac(String izvodjac) {
        this.izvodjac = izvodjac;
    }

    public BigDecimal getStaraCena() {
        return staraCena;
    }

    public void setStaraCena(BigDecimal staraCena) {
        this.staraCena = staraCena;
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
        final Album other = (Album) obj;
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.slika, other.slika)) {
            return false;
        }
        if (!Objects.equals(this.izvodjac, other.izvodjac)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        if (this.zanr != other.zanr) {
            return false;
        }
        if (!Objects.equals(this.staraCena, other.staraCena)) {
            return false;
        }
        if (!Objects.equals(this.cena, other.cena)) {
            return false;
        }
        if (!Objects.equals(this.dostupnaKolicina, other.dostupnaKolicina)) {
            return false;
        }
        if (!Objects.equals(this.poreskaStopa, other.poreskaStopa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", naziv=" + naziv + ", datumIzdavanja=" + datumIzdavanja + ", zanr=" + zanr + ", staraCena=" + staraCena + ", cena=" + cena + ", dostupnaKolicina=" + dostupnaKolicina + ", slika=" + slika + ", izvodjac=" + izvodjac + ", poreskaStopa=" + poreskaStopa + '}';
    }



    

}
