/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.controller;

import demo.albumshop.domain.Valuta;
import demo.albumshop.domain.Zanr;
import demo.albumshop.domain.dto.AlbumDTO;
import demo.albumshop.domain.dto.PoreskaStopaDTO;
import demo.albumshop.konvertor.Konvertor;
import demo.albumshop.service.AlbumService;
import demo.albumshop.service.PoreskaStopaService;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rancha
 */
@Controller
@RequestMapping(value = "/album/*")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PoreskaStopaService poreskaStopaService;

    private static final String UPLOADED_FOLDER = "C:\\Users\\rancha\\Documents\\NetBeansProjects\\MusicAlbumShop\\src\\main\\webapp\\resources\\images\\";

    @RequestMapping(value = "all/{page}", method = RequestMethod.GET)
    public ModelAndView allALbums(HttpSession session, @PathVariable("page") int page) {
        int limit = 8;
        Valuta valuta = (Valuta) session.getAttribute("valuta");
        Konvertor k = new Konvertor();
        List<AlbumDTO> albums = albumService.findAll();

        if (valuta != Valuta.RSD) {
            for (AlbumDTO album : albums) {
                String konRez = k.getRezultat("RSD", valuta.toString(), album.getCena());
                BigDecimal novaCena = new BigDecimal(konRez);
                novaCena = novaCena.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                album.setCena(novaCena);
                String konRez2 = k.getRezultat("RSD", valuta.toString(), album.getStaraCena());
                BigDecimal novaCena2 = new BigDecimal(konRez2);
                novaCena2 = novaCena2.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                album.setStaraCena(novaCena2);
            }
        }

        List<AlbumDTO> albumsPage = new ArrayList<>();

        double totalPages = Math.ceil((double) albums.size() / (double) limit);
        List<Long> pageNumbers = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNumbers.add(new Long(i));
        }
        int offset = limit * (page - 1);
        int granica = offset + limit;
        if (granica > albums.size()) {
            granica = albums.size();
        }
        for (int i = offset; i < granica; i++) {
            albumsPage.add(albums.get(i));
        }

        ModelAndView modelAndView = new ModelAndView("/album/all");
        modelAndView.addObject("albums", albumsPage);
        modelAndView.addObject("pageNumbers", pageNumbers);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(name = "zanr", required = false) String zanr,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {
        List<AlbumDTO> albums = new ArrayList<>();
        int limit = 8;
        int page = 1;
        Zanr z = Zanr.Jazz;
        if (zanr != null) {
            zanr = zanr.replace("/", "_");
            z = Zanr.valueOf(zanr);
        }
        if (zanr != null && minPrice != null && maxPrice != null) {
            albums = albumService.findByZanrAndCenaGreaterThanAndCenaLessThan(z, minPrice, maxPrice);
        }
        if (zanr != null && minPrice != null && maxPrice == null) {
            albums = albumService.findByZanrAndCenaGreaterThan(z, minPrice);
        }
        if (zanr != null && minPrice == null && maxPrice != null) {
            albums = albumService.findByZanrAndCenaLessThan(z, maxPrice);
        }
        if (zanr == null && minPrice != null && maxPrice != null) {
            albums = albumService.findByCenaGreaterThanAndCenaLessThan(minPrice, maxPrice);
        }
        if (zanr != null && minPrice == null && maxPrice == null) {
            albums = albumService.findByZanr(z);
        }
        if (zanr == null && minPrice != null && maxPrice == null) {
            albums = albumService.findByCenaGreaterThan(minPrice);
        }
        if (zanr == null && minPrice == null && maxPrice != null) {
            albums = albumService.findByCenaLessThan(maxPrice);
        }
        ModelAndView modelAndView = new ModelAndView("/album/all");
        if (albums.isEmpty() && (zanr != null || minPrice != null || maxPrice != null)) {
            modelAndView.addObject("poruka", "Sistem ne moze da pronadje albume po zadatoj vrednosti.");
        }
        if (albums.isEmpty() && zanr == null && minPrice == null && maxPrice == null) {
            modelAndView.addObject("poruka", "Unesite neki parametar pretrage!");
            albums = albumService.findAll();
        }
        if (albums.size() > limit) {
            List<AlbumDTO> albumsPage = new ArrayList<>();

            double totalPages = Math.ceil((double) albums.size() / (double) limit);
            List<Long> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(new Long(i));
            }
            int offset = limit * (page - 1);
            int granica = offset + limit;
            if (granica > albums.size()) {
                granica = albums.size();
            }
            for (int i = offset; i < granica; i++) {
                albumsPage.add(albums.get(i));
            }
            modelAndView.addObject("albums", albumsPage);
            modelAndView.addObject("pageNumbers", pageNumbers);
            modelAndView.addObject("totalPages", totalPages);
            modelAndView.addObject("page", page);
        } else {
            modelAndView.addObject("albums", albums);
        }

        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "/album/add";
    }


    @ModelAttribute(name = "album")
    public AlbumDTO album() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return new AlbumDTO("", sdf.format(new Date()), new BigDecimal(0), new BigDecimal(0), 0l, "", "", new PoreskaStopaDTO());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveAlbum(@ModelAttribute(name = "album") @Valid AlbumDTO albumDTO,
            @RequestParam("file") MultipartFile file,
            @RequestParam("iznosPS") String iznos,
            @RequestParam("zanrCombo") String zanr,
            BindingResult bindingResult,
            Model m) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("albumUnosErr", "Sistem ne moze da zapamti album.");
            return "/album/add";
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        PoreskaStopaDTO psDTO = poreskaStopaService.findByIznos(Double.parseDouble(iznos));
        if (psDTO == null) {
            psDTO = new PoreskaStopaDTO();
            psDTO.setIznos(Double.parseDouble(iznos));
            psDTO = poreskaStopaService.save(psDTO);
        }

        if (zanr.contains("/")) {
            zanr = zanr.replace("/", "_");
        }

        Zanr z = Zanr.valueOf(zanr);
        albumDTO.setZanr(z);
        albumDTO.setPoreskaStopa(psDTO);
        albumDTO.setSlika("/resources/images/" + file.getOriginalFilename());
        albumService.save(albumDTO);
        m.addAttribute("albumUnos", "Sistem je zapamtio novi album.");
        return "redirect:/album/all/1";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute(name = "viewAlbum") @Valid AlbumDTO albumDTO,
            @RequestParam("iznosPS") String iznos,
            @RequestParam("zanrCombo") String zanr,
            BindingResult bindingResult,
            Model m) {

        if (bindingResult.hasErrors()) {
            m.addAttribute("albumUnosErr", "Sistem ne moze da izmeni album.");
            return "album/all";
        }

        PoreskaStopaDTO psDTO = poreskaStopaService.findByIznos(Double.parseDouble(iznos));
        if (psDTO == null) {
            psDTO = new PoreskaStopaDTO();
            psDTO.setIznos(Double.parseDouble(iznos));
            psDTO = poreskaStopaService.save(psDTO);
        }
        if (zanr.contains("/")) {
            zanr = zanr.replace("/", "_");
        }

        Zanr z = Zanr.valueOf(zanr);
        albumDTO.setZanr(z);
        albumDTO.setPoreskaStopa(psDTO);
        albumService.save(albumDTO);
        m.addAttribute("albumUnos", "Sistem je izmenio album.");
        return "redirect:/album/all/1";
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/album/view");
        AlbumDTO album = albumService.findById(id);
        modelAndView.addObject("viewAlbum", album);
        return modelAndView;
    }

    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView details(@PathVariable("id") Long id, Model m) {
        ModelAndView modelAndView = new ModelAndView("/album/details");
        AlbumDTO album = albumService.findById(id);
        
        modelAndView.addObject("albumDetail", album);
        return modelAndView;
    }

    @RequestMapping(value = "imgDetails/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView imgDetails(@PathVariable("id") Long id, Model m) {
        ModelAndView modelAndView = new ModelAndView("/album/slika");
        AlbumDTO album = albumService.findById(id);
        modelAndView.addObject("albumZaPromenuSlike", album);
        return modelAndView;
    }

    @RequestMapping(value = "promeniSliku", method = RequestMethod.POST)
    public String updateImg(@RequestParam("albumID") Long albumID,
            @RequestParam("file") MultipartFile file) {
        AlbumDTO album = albumService.findById(albumID);
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setSlika("/resources/images/" + file.getOriginalFilename());
        albumService.save(album);
        return "redirect:/album/get/" + album.getId();
    }

    @RequestMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model m) {
        int limit = 8;
        int page = 1;
        albumService.delete(id);
        ModelAndView modelAndView = new ModelAndView("/album/all");
        List<AlbumDTO> albums = albumService.findAll();
        if (albums.size() > limit) {
            List<AlbumDTO> albumsPage = new ArrayList<>();

            double totalPages = Math.ceil((double) albums.size() / (double) limit);
            List<Long> pageNumbers = new ArrayList<>();
            for (int i = 1; i <= totalPages; i++) {
                pageNumbers.add(new Long(i));
            }
            int offset = limit * (page - 1);
            int granica = offset + limit;
            if (granica > albums.size()) {
                granica = albums.size();
            }
            for (int i = offset; i < granica; i++) {
                albumsPage.add(albums.get(i));
            }
            modelAndView.addObject("albums", albumsPage);
            modelAndView.addObject("pageNumbers", pageNumbers);
            modelAndView.addObject("totalPages", totalPages);
            modelAndView.addObject("page", page);
        } else {
            modelAndView.addObject("albums", albums);
        }
        modelAndView.addObject("poruka", "Sistem je obrisao album.");
        return modelAndView;
    }

}
