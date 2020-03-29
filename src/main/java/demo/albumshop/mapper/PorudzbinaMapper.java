/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.Porudzbina;
import demo.albumshop.domain.dto.PorudzbinaDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring", uses = {
    PotrosackaKorpaMapper.class,
    KorisnikMapper.class
})
public interface PorudzbinaMapper {

    @Mappings({
        @Mapping(target = "datumPorudzbine", source = "porudzbina.datumPorudzbine",
                dateFormat = "dd-MM-yyyy")
        ,
        @Mapping(target = "datumDostave", source = "porudzbina.datumDostave",
                dateFormat = "dd-MM-yyyy")
    })
    PorudzbinaDTO porudzbinaToPorudzbinaDTO(Porudzbina porudzbina);

    @Mappings({
        @Mapping(target = "datumPorudzbine", source = "porudzbinaDTO.datumPorudzbine",
                dateFormat = "dd-MM-yyyy")
        ,
        @Mapping(target = "datumDostave", source = "porudzbinaDTO.datumDostave",
                dateFormat = "dd-MM-yyyy")
    })
    Porudzbina porudzbinaDTOToPorudzbina(PorudzbinaDTO porudzbinaDTO);

    List<PorudzbinaDTO> toPorudzbinaDTOs(List<Porudzbina> porudzbine);
}
