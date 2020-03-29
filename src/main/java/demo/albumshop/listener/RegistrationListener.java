/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.listener;

import demo.albumshop.domain.Korisnik;
import demo.albumshop.event.OnRegistrationCompleteEvent;
import demo.albumshop.service.KorisnikService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 *
 * @author rancha
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{
    @Autowired
    private KorisnikService service;
  
    @Autowired
    private JavaMailSender mailSender;
 
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }
 
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        Korisnik user = event.getKorisnik();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
         
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl 
          = event.getAppUrl() + "/regitrationConfirm?token=" + token;
         
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setFrom("progrecordshop@gmail.com");
        email.setText("To confirm your account, please click here rn " + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}
