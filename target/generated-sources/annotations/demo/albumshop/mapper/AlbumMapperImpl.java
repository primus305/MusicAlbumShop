package demo.albumshop.mapper;

import demo.albumshop.domain.Album;
import demo.albumshop.domain.dto.AlbumDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T01:28:52+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class AlbumMapperImpl implements AlbumMapper {

    @Autowired
    private PoreskaStopaMapper poreskaStopaMapper;

    @Override
    public AlbumDTO albumToAlbumDTO(Album album) {
        if ( album == null ) {
            return null;
        }

        AlbumDTO albumDTO = new AlbumDTO();

        if ( album.getDatumIzdavanja() != null ) {
            albumDTO.setDatumIzdavanja( new SimpleDateFormat( "dd-MM-yyyy" ).format( album.getDatumIzdavanja() ) );
        }
        albumDTO.setId( album.getId() );
        albumDTO.setNaziv( album.getNaziv() );
        albumDTO.setZanr( album.getZanr() );
        albumDTO.setStaraCena( album.getStaraCena() );
        albumDTO.setCena( album.getCena() );
        albumDTO.setDostupnaKolicina( album.getDostupnaKolicina() );
        albumDTO.setSlika( album.getSlika() );
        albumDTO.setIzvodjac( album.getIzvodjac() );
        albumDTO.setPoreskaStopa( poreskaStopaMapper.poreskaStopaToPoreskaStopaDTO( album.getPoreskaStopa() ) );

        return albumDTO;
    }

    @Override
    public Album albumDTOToAlbum(AlbumDTO albumDTO) {
        if ( albumDTO == null ) {
            return null;
        }

        Album album = new Album();

        try {
            if ( albumDTO.getDatumIzdavanja() != null ) {
                album.setDatumIzdavanja( new SimpleDateFormat( "dd-MM-yyyy" ).parse( albumDTO.getDatumIzdavanja() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        album.setPoreskaStopa( poreskaStopaMapper.poreskaStopaDTOToPoreskaStopa( albumDTO.getPoreskaStopa() ) );
        album.setId( albumDTO.getId() );
        album.setNaziv( albumDTO.getNaziv() );
        album.setZanr( albumDTO.getZanr() );
        album.setCena( albumDTO.getCena() );
        album.setDostupnaKolicina( albumDTO.getDostupnaKolicina() );
        album.setSlika( albumDTO.getSlika() );
        album.setIzvodjac( albumDTO.getIzvodjac() );
        album.setStaraCena( albumDTO.getStaraCena() );

        return album;
    }

    @Override
    public List<AlbumDTO> toAlbumDTOs(List<Album> albums) {
        if ( albums == null ) {
            return null;
        }

        List<AlbumDTO> list = new ArrayList<AlbumDTO>( albums.size() );
        for ( Album album : albums ) {
            list.add( albumToAlbumDTO( album ) );
        }

        return list;
    }
}
