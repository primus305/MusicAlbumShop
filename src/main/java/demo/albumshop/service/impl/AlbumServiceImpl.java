/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.Zanr;
import demo.albumshop.domain.dto.AlbumDTO;
import demo.albumshop.mapper.AlbumMapper;
import java.math.BigDecimal;
import demo.albumshop.domain.Album;
import demo.albumshop.repository.AlbumRepository;
import demo.albumshop.service.AlbumService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rancha
 */
@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;
    
    @Autowired
    private AlbumMapper albumMapper;

    
    @Override
    public List<AlbumDTO> findAll() {
        return albumMapper.toAlbumDTOs(albumRepository.findAll());
    }

    @Override
    public AlbumDTO save(AlbumDTO albumDTO) {
        Album album = albumRepository.save(albumMapper.albumDTOToAlbum(albumDTO));
        return albumMapper.albumToAlbumDTO(album);
    }

    @Override
    public AlbumDTO findById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        
        if (album.isPresent()) {
            return albumMapper.albumToAlbumDTO(album.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public List<AlbumDTO> findByZanrAndCenaGreaterThanAndCenaLessThan(Zanr zanr, BigDecimal cenaOd, BigDecimal cenaDo) {
        return albumMapper.toAlbumDTOs(albumRepository.findByZanrAndCenaGreaterThanAndCenaLessThan(zanr, cenaOd, cenaDo));
    }

    @Override
    public List<AlbumDTO> findByCenaGreaterThanAndCenaLessThan(BigDecimal cenaOd, BigDecimal cenaDo) {
        return albumMapper.toAlbumDTOs(albumRepository.findByCenaGreaterThanAndCenaLessThan(cenaOd, cenaDo));
    }

    @Override
    public List<AlbumDTO> findByZanrAndCenaLessThan(Zanr zanr, BigDecimal cenaDo) {
        return albumMapper.toAlbumDTOs(albumRepository.findByZanrAndCenaLessThan(zanr, cenaDo));
    }

    @Override
    public List<AlbumDTO> findByZanrAndCenaGreaterThan(Zanr zanr, BigDecimal cenaOd) {
        return albumMapper.toAlbumDTOs(albumRepository.findByZanrAndCenaGreaterThan(zanr, cenaOd));
    }

    @Override
    public List<AlbumDTO> findByCenaLessThan(BigDecimal cenaDo) {
        return albumMapper.toAlbumDTOs(albumRepository.findByCenaLessThan(cenaDo));
    }

    @Override
    public List<AlbumDTO> findByCenaGreaterThan(BigDecimal cenaOd) {
        return albumMapper.toAlbumDTOs(albumRepository.findByCenaGreaterThan(cenaOd));
    }

    @Override
    public List<AlbumDTO> findByZanr(Zanr zanr) {
        return albumMapper.toAlbumDTOs(albumRepository.findByZanr(zanr));
    }
    
}
