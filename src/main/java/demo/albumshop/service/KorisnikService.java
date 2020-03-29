/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.VerificationToken;
import demo.albumshop.domain.dto.KorisnikDTO;
import java.util.List;

/**
 *
 * @author rancha
 */
public interface KorisnikService {
    List<KorisnikDTO> findAll();
    Korisnik save(KorisnikDTO korisnikDTO);
    void createVerificationToken(Korisnik korisnik, String token);
    VerificationToken getVerificationToken(String token);
    KorisnikDTO findById(Long id);
}
