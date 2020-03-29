package demo.albumshop.mapper;

import demo.albumshop.domain.Porudzbina;
import demo.albumshop.domain.dto.PorudzbinaDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T01:28:51+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class PorudzbinaMapperImpl implements PorudzbinaMapper {

    @Autowired
    private PotrosackaKorpaMapper potrosackaKorpaMapper;
    @Autowired
    private KorisnikMapper korisnikMapper;

    @Override
    public PorudzbinaDTO porudzbinaToPorudzbinaDTO(Porudzbina porudzbina) {
        if ( porudzbina == null ) {
            return null;
        }

        PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO();

        if ( porudzbina.getDatumPorudzbine() != null ) {
            porudzbinaDTO.setDatumPorudzbine( new SimpleDateFormat( "dd-MM-yyyy" ).format( porudzbina.getDatumPorudzbine() ) );
        }
        if ( porudzbina.getDatumDostave() != null ) {
            porudzbinaDTO.setDatumDostave( new SimpleDateFormat( "dd-MM-yyyy" ).format( porudzbina.getDatumDostave() ) );
        }
        porudzbinaDTO.setId( porudzbina.getId() );
        porudzbinaDTO.setDrzava( porudzbina.getDrzava() );
        porudzbinaDTO.setGrad( porudzbina.getGrad() );
        porudzbinaDTO.setAdresa( porudzbina.getAdresa() );
        porudzbinaDTO.setPostanskiBroj( porudzbina.getPostanskiBroj() );
        porudzbinaDTO.setPayed( porudzbina.isPayed() );
        porudzbinaDTO.setPotrosackaKorpa( potrosackaKorpaMapper.potrosackaKorpaToPotrosackaKorpaDTO( porudzbina.getPotrosackaKorpa() ) );
        porudzbinaDTO.setKorisnik( korisnikMapper.korisnikToKorisnikDTO( porudzbina.getKorisnik() ) );

        return porudzbinaDTO;
    }

    @Override
    public Porudzbina porudzbinaDTOToPorudzbina(PorudzbinaDTO porudzbinaDTO) {
        if ( porudzbinaDTO == null ) {
            return null;
        }

        Porudzbina porudzbina = new Porudzbina();

        try {
            if ( porudzbinaDTO.getDatumPorudzbine() != null ) {
                porudzbina.setDatumPorudzbine( new SimpleDateFormat( "dd-MM-yyyy" ).parse( porudzbinaDTO.getDatumPorudzbine() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( porudzbinaDTO.getDatumDostave() != null ) {
                porudzbina.setDatumDostave( new SimpleDateFormat( "dd-MM-yyyy" ).parse( porudzbinaDTO.getDatumDostave() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        porudzbina.setId( porudzbinaDTO.getId() );
        porudzbina.setDrzava( porudzbinaDTO.getDrzava() );
        porudzbina.setGrad( porudzbinaDTO.getGrad() );
        porudzbina.setAdresa( porudzbinaDTO.getAdresa() );
        porudzbina.setPotrosackaKorpa( potrosackaKorpaMapper.potrosackaKorpaDTOToPotrosackaKorpa( porudzbinaDTO.getPotrosackaKorpa() ) );
        porudzbina.setKorisnik( korisnikMapper.korisnikDTOToKorisnik( porudzbinaDTO.getKorisnik() ) );
        porudzbina.setPostanskiBroj( porudzbinaDTO.getPostanskiBroj() );
        porudzbina.setPayed( porudzbinaDTO.isPayed() );

        return porudzbina;
    }

    @Override
    public List<PorudzbinaDTO> toPorudzbinaDTOs(List<Porudzbina> porudzbine) {
        if ( porudzbine == null ) {
            return null;
        }

        List<PorudzbinaDTO> list = new ArrayList<PorudzbinaDTO>( porudzbine.size() );
        for ( Porudzbina porudzbina : porudzbine ) {
            list.add( porudzbinaToPorudzbinaDTO( porudzbina ) );
        }

        return list;
    }
}
