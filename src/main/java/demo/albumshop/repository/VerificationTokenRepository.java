/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.repository;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rancha
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
    VerificationToken findByToken(String token);
    VerificationToken findByKorisnik(Korisnik korisnik);
}
