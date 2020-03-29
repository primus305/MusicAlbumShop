/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.domain.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import demo.albumshop.repository.KorisnikRepository;

/**
 *
 * @author rancha
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private KorisnikRepository userRepo;
    
    @Autowired
    public UserRepositoryUserDetailsService(KorisnikRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Korisnik user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }
    

}
