package demo.albumshop.mapper;

import demo.albumshop.domain.PoreskaStopa;
import demo.albumshop.domain.dto.PoreskaStopaDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T01:28:52+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class PoreskaStopaMapperImpl implements PoreskaStopaMapper {

    @Override
    public PoreskaStopaDTO poreskaStopaToPoreskaStopaDTO(PoreskaStopa poreskaStopa) {
        if ( poreskaStopa == null ) {
            return null;
        }

        PoreskaStopaDTO poreskaStopaDTO = new PoreskaStopaDTO();

        poreskaStopaDTO.setId( poreskaStopa.getId() );
        poreskaStopaDTO.setIznos( poreskaStopa.getIznos() );

        return poreskaStopaDTO;
    }

    @Override
    public PoreskaStopa poreskaStopaDTOToPoreskaStopa(PoreskaStopaDTO poreskaStopaDTO) {
        if ( poreskaStopaDTO == null ) {
            return null;
        }

        PoreskaStopa poreskaStopa = new PoreskaStopa();

        poreskaStopa.setId( poreskaStopaDTO.getId() );
        poreskaStopa.setIznos( poreskaStopaDTO.getIznos() );

        return poreskaStopa;
    }
}
