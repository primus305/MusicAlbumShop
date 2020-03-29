/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain.dto;

import demo.albumshop.domain.Zanr;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author rancha
 */
@Data
public class AlbumDTO {

    private Long id;
    private String naziv;
    private String datumIzdavanja;
    private Zanr zanr;
    private BigDecimal staraCena;
    private BigDecimal cena;
    private Long dostupnaKolicina;
    private String slika;
    private String izvodjac;
    private PoreskaStopaDTO poreskaStopa;

    public AlbumDTO(String naziv, String datumIzdavanja, BigDecimal staraCena, BigDecimal cena, Long dostupnaKolicina, String slika, String izvodjac, PoreskaStopaDTO poreskaStopa) {
        this.naziv = naziv;
        this.datumIzdavanja = datumIzdavanja;
        this.staraCena = staraCena;
        this.cena = cena;
        this.dostupnaKolicina = dostupnaKolicina;
        this.slika = slika;
        this.izvodjac = izvodjac;
        this.poreskaStopa = poreskaStopa;
    }

    public AlbumDTO() {
    }

}
