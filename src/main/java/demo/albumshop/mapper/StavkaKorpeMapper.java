/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.StavkaKorpe;
import demo.albumshop.domain.dto.StavkaKorpeDTO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring", uses = {
    PotrosackaKorpaMapper.class,
    AlbumMapper.class
})
public interface StavkaKorpeMapper {
    StavkaKorpeDTO stavkaKorpeToStavkaKorpeDTO(StavkaKorpe stavkaKorpe);
    StavkaKorpe stavkaKorpeDTOToStavkaKorpe(StavkaKorpeDTO stavkaKorpeDTO);
    List<StavkaKorpeDTO> toStavkaKorpeDTOs(List<StavkaKorpe> stavke);
    List<StavkaKorpe> toStavkaKorpes(List<StavkaKorpeDTO> stavkeDTO);
}
