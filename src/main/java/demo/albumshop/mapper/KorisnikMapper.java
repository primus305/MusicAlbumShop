/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.dto.KorisnikDTO;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring")
public interface KorisnikMapper {
    KorisnikDTO korisnikToKorisnikDTO(Korisnik korisnik);
    
    Korisnik korisnikDTOToKorisnik(KorisnikDTO korisnikDTO);
    
    List<KorisnikDTO> toKorisnikDTOs(List<Korisnik> korisnici);
}
