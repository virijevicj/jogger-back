package com.jogger.rs.junit;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.config.AppConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class AppConfigTest {

    AppConfig appConfig;

    @BeforeEach
    void setUp() throws Exception {
        appConfig = new AppConfig(new SessionManager());
    }

    @AfterEach
    void tearDown() throws Exception {
        appConfig = null;
    }

    @Test
    @DisplayName("AppConfigTest - kreiranje BCryptPasswordEncoder objekta")
    void bCrypt() {
        assertInstanceOf(BCryptPasswordEncoder.class, appConfig.bCrypt());
    }

    @Test
    @DisplayName("AppConfigTest - kreiranje ModelMapper objekta")
    void modelMapper() {
        assertInstanceOf(ModelMapper.class, appConfig.modelMapper());
    }
}
