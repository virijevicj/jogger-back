package com.jogger.rs.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Globalna konfiguracija aplikacije plus kreiranje spring binova preko metoda
 *
 * @author Jovan Virijevic
 */
@Configuration
@EnableWebMvc
@EnableAsync
public class AppConfig implements WebMvcConfigurer {

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
