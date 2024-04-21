package com.jogger.rs.config;

import com.jogger.rs.jwt.JWTFilter;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.role.RoleName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Globalna konfiguracija bezbednosti aplikacije
 *
 * @author Jovan Virijevic
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("server.servlet.context-path")
    private static String CONTEXT_PATH;

    /**
     * Metoda koja konfigurise security filter chain
     *
     * @param http objekat koji se konfigurise
     * @param jwtFilter filter koji se dodaje za autentifikaciju
     * @return konfigurisan http objekat
     * @throws Exception ako konfiguracija nije moguca
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JWTFilter jwtFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(RequestMappingPrefix.AUTH + "/login").permitAll()
                                .requestMatchers(HttpMethod.GET, CONTEXT_PATH  + RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES)
                                    .hasAnyRole(RoleName.Developer.name(), RoleName.Intern.name())
                                .requestMatchers(HttpMethod.GET, CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL)
                                    .hasAnyRole(RoleName.Developer.name(), RoleName.Intern.name())
                                .requestMatchers(
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL,
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL + "/**",
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL_COMMENT,
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL_COMMENT + "/**",
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES,
                                        CONTEXT_PATH + RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES + "/**")
                                    .hasRole(RoleName.Developer.name())
                                .requestMatchers(
                                        CONTEXT_PATH + RequestMappingPrefix.USER,
                                        CONTEXT_PATH + RequestMappingPrefix.USER + "/**")
                                    .hasRole(RoleName.Admin.name())
                                .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
