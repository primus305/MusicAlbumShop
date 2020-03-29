/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain.dto;

import demo.albumshop.domain.Valuta;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author rancha
 */
@Data
public class PotrosackaKorpaDTO {
    private Long id;
    private Valuta valuta;
    private BigDecimal ukupanIznos;
    private BigDecimal ukupanIznosPDV;
    private List<StavkaKorpeDTO> stavkeDTOs;

    public PotrosackaKorpaDTO(Valuta valuta, BigDecimal ukupanIznos, BigDecimal ukupanIznosPDV) {
        this.valuta = valuta;
        this.ukupanIznos = ukupanIznos;
        this.ukupanIznosPDV = ukupanIznosPDV;
        stavkeDTOs = new ArrayList<>();
    }

    public PotrosackaKorpaDTO() {
        stavkeDTOs = new ArrayList<>();
    }
    
    public void dodajStavku(StavkaKorpeDTO stavkaKorpe) {
        stavkeDTOs.add(stavkaKorpe);
    }

    public void obrisiStavku(StavkaKorpeDTO stavkaKorpe) {
        stavkeDTOs.remove(stavkaKorpe);
        int rb = 0;
        for (StavkaKorpeDTO sk : stavkeDTOs) {
            sk.setRedniBroj(new Long(++rb));
        }
    }

    public void srediUkupanIznos() {
        ukupanIznos = new BigDecimal(0);
        for (StavkaKorpeDTO stavkaKorpe : stavkeDTOs) {
            BigDecimal quantity = new BigDecimal(stavkaKorpe.getQuantity());
            BigDecimal iznos = stavkaKorpe.getCena().multiply(quantity);
            ukupanIznos = ukupanIznos.add(iznos);
        }
    }

    public void srediUkupanIznosPDV() {
        ukupanIznosPDV = new BigDecimal(0);
        for (StavkaKorpeDTO stavkaKorpe : stavkeDTOs) {
            BigDecimal quantity = new BigDecimal(stavkaKorpe.getQuantity());
            BigDecimal iznos = stavkaKorpe.getUkupnaCena().multiply(quantity);
            ukupanIznosPDV = ukupanIznosPDV.add(iznos);
        }
    }
}
