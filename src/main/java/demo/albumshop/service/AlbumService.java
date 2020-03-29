/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service;

import demo.albumshop.domain.Album;
import demo.albumshop.domain.Zanr;
import demo.albumshop.domain.dto.AlbumDTO;
import java.math.BigDecimal;
import java.util.List;



/**
 *
 * @author rancha
 */
public interface AlbumService {
    List<AlbumDTO> findAll();

    AlbumDTO save(AlbumDTO albumDTO);

    AlbumDTO findById(Long id);
    
    void delete(Long id);
    
    List<AlbumDTO> findByZanrAndCenaGreaterThanAndCenaLessThan(Zanr zanr, BigDecimal cenaOd, BigDecimal cenaDo);

    List<AlbumDTO> findByCenaGreaterThanAndCenaLessThan(BigDecimal cenaOd, BigDecimal cenaDo);

    List<AlbumDTO> findByZanrAndCenaLessThan(Zanr zanr, BigDecimal cenaDo);

    List<AlbumDTO> findByZanrAndCenaGreaterThan(Zanr zanr, BigDecimal cenaOd);

    List<AlbumDTO> findByCenaLessThan(BigDecimal cenaDo);

    List<AlbumDTO> findByCenaGreaterThan(BigDecimal cenaOd);

    List<AlbumDTO> findByZanr(Zanr zanr);
}
