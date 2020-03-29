/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.repository;

import demo.albumshop.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rancha
 */
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
    Korisnik findByUsername(String username);
}
