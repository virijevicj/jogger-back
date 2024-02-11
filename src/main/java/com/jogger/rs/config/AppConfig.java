package com.jogger.rs.config;

import com.jogger.rs.auth.SessionManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * Globalna konfiguracija aplikacije plus kreiranje spring binova preko metoda
 *
 * @author Jovan Virijevic
 */
@Configuration
@EnableScheduling
@EnableWebMvc
@EnableAsync
public class AppConfig implements WebMvcConfigurer {

    /**
     * Servis koji vodi racuna o korisnickim sesijama
     */
    private SessionManager sessionManager;

    /**
     * Javni konstruktor
     *
     * @param sessionManager servis koji vodi racuna o korisnickim sesijama
     */
    @Autowired
    public AppConfig(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Job koji se okida na svakih 5 dana i prazni mapu korisnickih sesija
     */
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.DAYS)
    private void clearAllUserSessions() {
        sessionManager.clearAllSessions();
    }

    /**
     * Metoda koja kreira bean BCryptPasswordEncoder
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder bCrypt() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Metoda koja kreira bean ModelMapper
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Metoda koja definise koje metode smeju da dele svoje resurse
     *
     * @param registry konfigurisan CorsRegistry objekat sa metodama kojima je dozvoljeno da dele resurse aplikacije
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
