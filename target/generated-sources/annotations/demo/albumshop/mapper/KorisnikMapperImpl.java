package demo.albumshop.mapper;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.domain.dto.KorisnikDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-29T01:28:52+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class KorisnikMapperImpl implements KorisnikMapper {

    @Override
    public KorisnikDTO korisnikToKorisnikDTO(Korisnik korisnik) {
        if ( korisnik == null ) {
            return null;
        }

        KorisnikDTO korisnikDTO = new KorisnikDTO();

        korisnikDTO.setId( korisnik.getId() );
        korisnikDTO.setUsername( korisnik.getUsername() );
        korisnikDTO.setPassword( korisnik.getPassword() );
        korisnikDTO.setFirstname( korisnik.getFirstname() );
        korisnikDTO.setLastname( korisnik.getLastname() );
        korisnikDTO.setEmail( korisnik.getEmail() );
        korisnikDTO.setUloga( korisnik.getUloga() );
        korisnikDTO.setEnabled( korisnik.isEnabled() );

        return korisnikDTO;
    }

    @Override
    public Korisnik korisnikDTOToKorisnik(KorisnikDTO korisnikDTO) {
        if ( korisnikDTO == null ) {
            return null;
        }

        Korisnik korisnik = new Korisnik();

        korisnik.setId( korisnikDTO.getId() );
        korisnik.setUsername( korisnikDTO.getUsername() );
        korisnik.setPassword( korisnikDTO.getPassword() );
        korisnik.setFirstname( korisnikDTO.getFirstname() );
        korisnik.setLastname( korisnikDTO.getLastname() );
        korisnik.setEmail( korisnikDTO.getEmail() );
        korisnik.setUloga( korisnikDTO.getUloga() );
        korisnik.setEnabled( korisnikDTO.isEnabled() );

        return korisnik;
    }

    @Override
    public List<KorisnikDTO> toKorisnikDTOs(List<Korisnik> korisnici) {
        if ( korisnici == null ) {
            return null;
        }

        List<KorisnikDTO> list = new ArrayList<KorisnikDTO>( korisnici.size() );
        for ( Korisnik korisnik : korisnici ) {
            list.add( korisnikToKorisnikDTO( korisnik ) );
        }

        return list;
    }
}
