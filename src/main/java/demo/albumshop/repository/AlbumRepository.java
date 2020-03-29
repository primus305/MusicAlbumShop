/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.repository;

import demo.albumshop.domain.Album;
import demo.albumshop.domain.Zanr;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rancha
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{
    List<Album> findByZanrAndCenaGreaterThanAndCenaLessThan(Zanr zanr, BigDecimal cenaOd, BigDecimal cenaDo);
    List<Album> findByCenaGreaterThanAndCenaLessThan(BigDecimal cenaOd, BigDecimal cenaDo);
    List<Album> findByZanrAndCenaLessThan(Zanr zanr, BigDecimal cenaDo);
    List<Album> findByZanrAndCenaGreaterThan(Zanr zanr, BigDecimal cenaOd);
    List<Album> findByCenaLessThan(BigDecimal cenaDo);
    List<Album> findByCenaGreaterThan(BigDecimal cenaOd);
    List<Album> findByZanr(Zanr zanr);
}
