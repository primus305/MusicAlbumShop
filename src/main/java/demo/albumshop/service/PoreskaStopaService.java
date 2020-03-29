/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service;

import demo.albumshop.domain.PoreskaStopa;
import demo.albumshop.domain.dto.PoreskaStopaDTO;

/**
 *
 * @author rancha
 */
public interface PoreskaStopaService {

    PoreskaStopaDTO save(PoreskaStopaDTO poreskaStopaDTO);

    PoreskaStopaDTO findByIznos(double iznos);
}
