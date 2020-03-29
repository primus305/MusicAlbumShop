/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.PotrosackaKorpa;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring", uses = {
    PorudzbinaMapper.class,
    StavkaKorpeMapper.class
})
public interface PotrosackaKorpaMapper {
    @Mapping(source = "stavke", target = "stavkeDTOs")
    PotrosackaKorpaDTO potrosackaKorpaToPotrosackaKorpaDTO(PotrosackaKorpa potrosackaKorpa);
//    PotrosackaKorpa potrosackaKorpaDTOToPotrosackaKorpa(PotrosackaKorpaDTO potrosackaKorpaDTO, @Context JpaContext ctx);
    @Mapping(source = "stavkeDTOs", target = "stavke")
    PotrosackaKorpa potrosackaKorpaDTOToPotrosackaKorpa(PotrosackaKorpaDTO potrosackaKorpaDTO);
}
