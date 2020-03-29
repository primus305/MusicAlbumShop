/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.dto.PorudzbinaDTO;
import java.util.List;

/**
 *
 * @author rancha
 */
public interface PorudzbinaService {

    List<PorudzbinaDTO> findAll(Korisnik korisnik);

    PorudzbinaDTO save(PorudzbinaDTO porudzbinaDTO, Korisnik korisnik);

    PorudzbinaDTO update(PorudzbinaDTO porudzbinaDTO);

    PorudzbinaDTO findById(Long id);
}
