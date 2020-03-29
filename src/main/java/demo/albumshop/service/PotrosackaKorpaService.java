/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service;

import demo.albumshop.domain.PotrosackaKorpa;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import java.util.List;

/**
 *
 * @author rancha
 */
public interface PotrosackaKorpaService {

    PotrosackaKorpaDTO save(PotrosackaKorpaDTO potrosackaKorpaDTO);

    PotrosackaKorpaDTO findById(Long id);
}
