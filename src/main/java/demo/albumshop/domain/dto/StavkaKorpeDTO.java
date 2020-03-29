/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author rancha
 */
@Data
public class StavkaKorpeDTO {

    private Long redniBroj;
    private AlbumDTO album;
    private BigDecimal cena;
    private BigDecimal ukupnaCena;
    private int quantity;

    public StavkaKorpeDTO(Long redniBroj, AlbumDTO album, BigDecimal cena, BigDecimal ukupnaCena, int quantity) {
        this.redniBroj = redniBroj;
        this.album = album;
        this.cena = cena;
        this.ukupnaCena = ukupnaCena;
        this.quantity = quantity;
    }

    public StavkaKorpeDTO() {
    }

}
