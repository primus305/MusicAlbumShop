/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author rancha
 */
public class StavkaPK implements Serializable {

    private Long redniBroj;

    private PotrosackaKorpa potrosackaKorpa;

    public StavkaPK() {
    }

    public StavkaPK(Long redniBroj, PotrosackaKorpa potrosackaKorpa) {
        this.redniBroj = redniBroj;
        this.potrosackaKorpa = potrosackaKorpa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaPK other = (StavkaPK) obj;
        if (!Objects.equals(this.redniBroj, other.redniBroj)) {
            return false;
        }
        if (!Objects.equals(this.potrosackaKorpa, other.potrosackaKorpa)) {
            return false;
        }
        return true;
    }
    
    

}
