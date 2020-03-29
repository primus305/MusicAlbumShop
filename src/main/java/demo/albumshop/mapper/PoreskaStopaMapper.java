/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.PoreskaStopa;
import demo.albumshop.domain.dto.PoreskaStopaDTO;
import org.mapstruct.Mapper;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring")
public interface PoreskaStopaMapper {

    PoreskaStopaDTO poreskaStopaToPoreskaStopaDTO(PoreskaStopa poreskaStopa);

    PoreskaStopa poreskaStopaDTOToPoreskaStopa(PoreskaStopaDTO poreskaStopaDTO);
}
