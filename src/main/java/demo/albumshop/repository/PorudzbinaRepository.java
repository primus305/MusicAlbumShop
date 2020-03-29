/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.repository;

import demo.albumshop.domain.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rancha
 */
@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long>{
    
}
