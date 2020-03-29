/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.VerificationToken;
import demo.albumshop.domain.dto.KorisnikDTO;
import demo.albumshop.mapper.KorisnikMapper;
import demo.albumshop.repository.KorisnikRepository;
import demo.albumshop.repository.VerificationTokenRepository;
import demo.albumshop.service.KorisnikService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author rancha
 */
@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private KorisnikMapper korisnikMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public List<KorisnikDTO> findAll() {
        return korisnikMapper.toKorisnikDTOs(korisnikRepository.findAll());
    }

    @Override
    public Korisnik save(KorisnikDTO korisnikDTO) {
        Korisnik k = korisnikMapper.korisnikDTOToKorisnik(korisnikDTO);
        if(!k.isEnabled()) {
            k.setPassword(passwordEncoder.encode(korisnikDTO.getPassword()));
        }
        return korisnikRepository.save(k);
    }

    @Override
    public void createVerificationToken(Korisnik korisnik, String token) {
        VerificationToken myToken = new VerificationToken(token, korisnik);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }
    
    @Override
    public KorisnikDTO findById(Long id) {
        Optional<Korisnik> korisnik = korisnikRepository.findById(id);

        if (korisnik.isPresent()) {
            return korisnikMapper.korisnikToKorisnikDTO(korisnik.get());
        }
        return null;
    }
}