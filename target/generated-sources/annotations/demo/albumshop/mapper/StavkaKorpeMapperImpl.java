package demo.albumshop.mapper;

import demo.albumshop.domain.StavkaKorpe;
import demo.albumshop.domain.dto.StavkaKorpeDTO;
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
public class StavkaKorpeMapperImpl implements StavkaKorpeMapper {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public StavkaKorpeDTO stavkaKorpeToStavkaKorpeDTO(StavkaKorpe stavkaKorpe) {
        if ( stavkaKorpe == null ) {
            return null;
        }

        StavkaKorpeDTO stavkaKorpeDTO = new StavkaKorpeDTO();

        stavkaKorpeDTO.setRedniBroj( stavkaKorpe.getRedniBroj() );
        stavkaKorpeDTO.setAlbum( albumMapper.albumToAlbumDTO( stavkaKorpe.getAlbum() ) );
        stavkaKorpeDTO.setCena( stavkaKorpe.getCena() );
        stavkaKorpeDTO.setUkupnaCena( stavkaKorpe.getUkupnaCena() );
        stavkaKorpeDTO.setQuantity( stavkaKorpe.getQuantity() );

        return stavkaKorpeDTO;
    }

    @Override
    public StavkaKorpe stavkaKorpeDTOToStavkaKorpe(StavkaKorpeDTO stavkaKorpeDTO) {
        if ( stavkaKorpeDTO == null ) {
            return null;
        }

        StavkaKorpe stavkaKorpe = new StavkaKorpe();

        stavkaKorpe.setRedniBroj( stavkaKorpeDTO.getRedniBroj() );
        stavkaKorpe.setAlbum( albumMapper.albumDTOToAlbum( stavkaKorpeDTO.getAlbum() ) );
        stavkaKorpe.setCena( stavkaKorpeDTO.getCena() );
        stavkaKorpe.setUkupnaCena( stavkaKorpeDTO.getUkupnaCena() );
        stavkaKorpe.setQuantity( stavkaKorpeDTO.getQuantity() );

        return stavkaKorpe;
    }

    @Override
    public List<StavkaKorpeDTO> toStavkaKorpeDTOs(List<StavkaKorpe> stavke) {
        if ( stavke == null ) {
            return null;
        }

        List<StavkaKorpeDTO> list = new ArrayList<StavkaKorpeDTO>( stavke.size() );
        for ( StavkaKorpe stavkaKorpe : stavke ) {
            list.add( stavkaKorpeToStavkaKorpeDTO( stavkaKorpe ) );
        }

        return list;
    }

    @Override
    public List<StavkaKorpe> toStavkaKorpes(List<StavkaKorpeDTO> stavkeDTO) {
        if ( stavkeDTO == null ) {
            return null;
        }

        List<StavkaKorpe> list = new ArrayList<StavkaKorpe>( stavkeDTO.size() );
        for ( StavkaKorpeDTO stavkaKorpeDTO : stavkeDTO ) {
            list.add( stavkaKorpeDTOToStavkaKorpe( stavkaKorpeDTO ) );
        }

        return list;
    }
}
