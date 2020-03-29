package demo.albumshop.mapper;

import demo.albumshop.domain.PotrosackaKorpa;
import demo.albumshop.domain.dto.PotrosackaKorpaDTO;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T01:28:52+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class PotrosackaKorpaMapperImpl implements PotrosackaKorpaMapper {

    @Autowired
    private StavkaKorpeMapper stavkaKorpeMapper;

    @Override
    public PotrosackaKorpaDTO potrosackaKorpaToPotrosackaKorpaDTO(PotrosackaKorpa potrosackaKorpa) {
        if ( potrosackaKorpa == null ) {
            return null;
        }

        PotrosackaKorpaDTO potrosackaKorpaDTO = new PotrosackaKorpaDTO();

        potrosackaKorpaDTO.setStavkeDTOs( stavkaKorpeMapper.toStavkaKorpeDTOs( potrosackaKorpa.getStavke() ) );
        potrosackaKorpaDTO.setId( potrosackaKorpa.getId() );
        potrosackaKorpaDTO.setValuta( potrosackaKorpa.getValuta() );
        potrosackaKorpaDTO.setUkupanIznos( potrosackaKorpa.getUkupanIznos() );
        potrosackaKorpaDTO.setUkupanIznosPDV( potrosackaKorpa.getUkupanIznosPDV() );

        return potrosackaKorpaDTO;
    }

    @Override
    public PotrosackaKorpa potrosackaKorpaDTOToPotrosackaKorpa(PotrosackaKorpaDTO potrosackaKorpaDTO) {
        if ( potrosackaKorpaDTO == null ) {
            return null;
        }

        PotrosackaKorpa potrosackaKorpa = new PotrosackaKorpa();

        potrosackaKorpa.setStavke( stavkaKorpeMapper.toStavkaKorpes( potrosackaKorpaDTO.getStavkeDTOs() ) );
        potrosackaKorpa.setId( potrosackaKorpaDTO.getId() );
        potrosackaKorpa.setValuta( potrosackaKorpaDTO.getValuta() );
        potrosackaKorpa.setUkupanIznos( potrosackaKorpaDTO.getUkupanIznos() );
        potrosackaKorpa.setUkupanIznosPDV( potrosackaKorpaDTO.getUkupanIznosPDV() );

        return potrosackaKorpa;
    }
}
