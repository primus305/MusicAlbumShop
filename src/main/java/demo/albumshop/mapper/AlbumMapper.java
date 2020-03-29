/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.mapper;

import demo.albumshop.domain.Album;
import demo.albumshop.domain.dto.AlbumDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author rancha
 */
@Mapper(componentModel = "spring", uses = PoreskaStopaMapper.class)
public interface AlbumMapper {

    @Mapping(target = "datumIzdavanja", source = "album.datumIzdavanja",
            dateFormat = "dd-MM-yyyy")
    AlbumDTO albumToAlbumDTO(Album album);

    @Mapping(target = "datumIzdavanja", source = "albumDTO.datumIzdavanja",
            dateFormat = "dd-MM-yyyy")
    Album albumDTOToAlbum(AlbumDTO albumDTO);

    List<AlbumDTO> toAlbumDTOs(List<Album> albums);
}
