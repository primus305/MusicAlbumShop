/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.Porudzbina;
import demo.albumshop.domain.PotrosackaKorpa;
import demo.albumshop.domain.StavkaKorpe;
import demo.albumshop.domain.dto.PorudzbinaDTO;
import demo.albumshop.mapper.PorudzbinaMapper;
import demo.albumshop.repository.PorudzbinaRepository;
import demo.albumshop.repository.PotrosackaKorpaRepository;
import demo.albumshop.service.PorudzbinaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rancha
 */
@Service
public class PorudzbinaServiceImpl implements PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PotrosackaKorpaRepository potrosackaKorpaRepository;

    @Autowired
    private PorudzbinaMapper porudzbinaMapper;

    @Override
    public List<PorudzbinaDTO> findAll(Korisnik korisnik) {
        List<Porudzbina> sve = porudzbinaRepository.findAll();
        List<PorudzbinaDTO> moje = new ArrayList<>();
        for (Porudzbina porudzbina : sve) {
            if (porudzbina.getKorisnik().getId().equals(korisnik.getId())) {
                PotrosackaKorpa pk = porudzbina.getPotrosackaKorpa();
                PorudzbinaDTO porudzbinaDTO = porudzbinaMapper.porudzbinaToPorudzbinaDTO(porudzbina);
                moje.add(porudzbinaDTO);
            }
        }
        return moje;
    }

    @Override
    public PorudzbinaDTO save(PorudzbinaDTO porudzbinaDTO, Korisnik korisnik) {
        Porudzbina p = porudzbinaMapper.porudzbinaDTOToPorudzbina(porudzbinaDTO);
        for (StavkaKorpe stavkaKorpe : p.getPotrosackaKorpa().getStavke()) {
            stavkaKorpe.setPotrosackaKorpa(p.getPotrosackaKorpa());
        }
        p.setKorisnik(korisnik);
        p = porudzbinaRepository.save(p);
        PotrosackaKorpa pk = p.getPotrosackaKorpa();
        pk.setPorudzbina(p);
        potrosackaKorpaRepository.save(pk);
        return porudzbinaMapper.porudzbinaToPorudzbinaDTO(p);
    }

    @Override
    public PorudzbinaDTO findById(Long id) {
        Optional<Porudzbina> porudzbina = porudzbinaRepository.findById(id);

        if (porudzbina.isPresent()) {
            return porudzbinaMapper.porudzbinaToPorudzbinaDTO(porudzbina.get());
        }
        return null;
    }

    @Override
    public PorudzbinaDTO update(PorudzbinaDTO porudzbinaDTO) {
        Porudzbina p = porudzbinaMapper.porudzbinaDTOToPorudzbina(porudzbinaDTO);
        for (StavkaKorpe stavkaKorpe : p.getPotrosackaKorpa().getStavke()) {
            stavkaKorpe.setPotrosackaKorpa(p.getPotrosackaKorpa());
        }
        p = porudzbinaRepository.save(p);
        PotrosackaKorpa pk = p.getPotrosackaKorpa();
        pk.setPorudzbina(p);
        potrosackaKorpaRepository.save(pk);
        return porudzbinaMapper.porudzbinaToPorudzbinaDTO(p);
    }
}
