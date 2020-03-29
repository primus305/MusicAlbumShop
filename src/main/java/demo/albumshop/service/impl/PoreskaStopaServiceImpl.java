/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.PoreskaStopa;
import demo.albumshop.domain.dto.PoreskaStopaDTO;
import demo.albumshop.mapper.PoreskaStopaMapper;
import demo.albumshop.repository.PoreskaStopaRepository;
import demo.albumshop.service.PoreskaStopaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rancha
 */
@Service
public class PoreskaStopaServiceImpl implements PoreskaStopaService {

    @Autowired
    private PoreskaStopaRepository poreskaStopaRepository;

    @Autowired
    private PoreskaStopaMapper poreskaStopaMapper;

    @Override
    public PoreskaStopaDTO save(PoreskaStopaDTO poreskaStopaDTO) {
        PoreskaStopa ps = poreskaStopaRepository.save(poreskaStopaMapper.poreskaStopaDTOToPoreskaStopa(poreskaStopaDTO));
        return poreskaStopaMapper.poreskaStopaToPoreskaStopaDTO(ps);
    }

    @Override
    public PoreskaStopaDTO findByIznos(double iznos) {
        return poreskaStopaMapper.poreskaStopaToPoreskaStopaDTO(poreskaStopaRepository.findByIznos(iznos));

    }
}
