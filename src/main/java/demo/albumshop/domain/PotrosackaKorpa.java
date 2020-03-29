/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author rancha
 */
@Entity
@Table(name = "potrosackaKorpa")
public class PotrosackaKorpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Valuta valuta;
    @NotNull
    private BigDecimal ukupanIznos;
    @NotNull
    private BigDecimal ukupanIznosPDV;
    @OneToOne
    private Porudzbina porudzbina;
    @OneToMany(
            mappedBy = "potrosackaKorpa",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<StavkaKorpe> stavke;

    public PotrosackaKorpa() {
        stavke = new ArrayList<>();
    }

    public PotrosackaKorpa(Valuta valuta, BigDecimal ukupanIznos, BigDecimal ukupanIznosPDV, Porudzbina porudzbina) {
        this.valuta = valuta;
        this.ukupanIznos = ukupanIznos;
        this.ukupanIznosPDV = ukupanIznosPDV;
        this.porudzbina = porudzbina;
        stavke = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(BigDecimal ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public BigDecimal getUkupanIznosPDV() {
        return ukupanIznosPDV;
    }

    public void setUkupanIznosPDV(BigDecimal ukupanIznosPDV) {
        this.ukupanIznosPDV = ukupanIznosPDV;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public List<StavkaKorpe> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaKorpe> stavke) {
        this.stavke = stavke;
    }

    public void dodajStavku(StavkaKorpe stavkaKorpe) {
        stavke.add(stavkaKorpe);
        stavkaKorpe.setPotrosackaKorpa(this);
    }

    public void obrisiStavku(StavkaKorpe stavkaKorpe) {
        stavke.remove(stavkaKorpe);
        stavkaKorpe.setPotrosackaKorpa(null);
        int rb = 0;
        for (StavkaKorpe sk : stavke) {
            sk.setRedniBroj(new Long(++rb));
        }
    }

    public void srediUkupanIznos() {
        ukupanIznos = new BigDecimal(0);
        for (StavkaKorpe stavkaKorpe : stavke) {
            BigDecimal quantity = new BigDecimal(stavkaKorpe.getQuantity());
            BigDecimal iznos = stavkaKorpe.getCena().multiply(quantity);
            ukupanIznos = ukupanIznos.add(iznos);
        }
    }

    public void srediUkupanIznosPDV() {
        ukupanIznosPDV = new BigDecimal(0);
        for (StavkaKorpe stavkaKorpe : stavke) {
            BigDecimal quantity = new BigDecimal(stavkaKorpe.getQuantity());
            BigDecimal iznos = stavkaKorpe.getUkupnaCena().multiply(quantity);
            ukupanIznosPDV = ukupanIznosPDV.add(iznos);
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final PotrosackaKorpa other = (PotrosackaKorpa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.valuta != other.valuta) {
            return false;
        }
        if (!Objects.equals(this.ukupanIznos, other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.ukupanIznosPDV, other.ukupanIznosPDV)) {
            return false;
        }
        if (!Objects.equals(this.porudzbina, other.porudzbina)) {
            return false;
        }
        if (!Objects.equals(this.stavke, other.stavke)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PotrosackaKorpa{" + "id=" + id + ", valuta=" + valuta + ", ukupanIznos=" + ukupanIznos + ", ukupanIznosPDV=" + ukupanIznosPDV + ", porudzbina=" + porudzbina + ", stavke=" + stavke + '}';
    }

    
}
