/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain.dto;

import demo.albumshop.domain.Uloga;
import lombok.Data;

/**
 *
 * @author rancha
 */
@Data
public class KorisnikDTO {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Uloga uloga;
    private boolean enabled;

    public KorisnikDTO(Uloga uloga) {
        this.uloga = uloga;
        this.enabled = false;
    }

    public KorisnikDTO() {
    }
    
    
}
