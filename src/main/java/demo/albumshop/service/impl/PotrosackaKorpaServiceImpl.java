/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.PotrosackaKorpa;
import demo.albumshop.domain.StavkaKorpe;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import demo.albumshop.mapper.PotrosackaKorpaMapper;
import demo.albumshop.repository.PotrosackaKorpaRepository;
import demo.albumshop.service.PotrosackaKorpaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rancha
 */
@Service
public class PotrosackaKorpaServiceImpl implements PotrosackaKorpaService {

    @Autowired
    private PotrosackaKorpaRepository potrosackaKorpaRepository;

    @Autowired
    private PotrosackaKorpaMapper potrosackaKorpaMapper;


    @Override
    public PotrosackaKorpaDTO save(PotrosackaKorpaDTO potrosackaKorpaDTO) {
        PotrosackaKorpa potrosackaKorpa = potrosackaKorpaMapper.potrosackaKorpaDTOToPotrosackaKorpa(potrosackaKorpaDTO);
        for (StavkaKorpe stavkaKorpe : potrosackaKorpa.getStavke()) {
            stavkaKorpe.setPotrosackaKorpa(potrosackaKorpa);
        }
        potrosackaKorpa = potrosackaKorpaRepository.save(potrosackaKorpa);
        return potrosackaKorpaMapper.potrosackaKorpaToPotrosackaKorpaDTO(potrosackaKorpa);
    }

    @Override
    public PotrosackaKorpaDTO findById(Long id) {
        Optional<PotrosackaKorpa> korpa = potrosackaKorpaRepository.findById(id);

        if (korpa.isPresent()) {
            return potrosackaKorpaMapper.potrosackaKorpaToPotrosackaKorpaDTO(korpa.get());
        }
        return null;
    }

}
