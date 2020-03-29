/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author rancha
 */
@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("progrecordshop@gmail.com");
        javaMailSender.setPassword("********");
        javaMailSender.setPort(587);
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smpt.auth", "true");
        javaMailProperties.setProperty("mail.smpt.port", "587");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

}
