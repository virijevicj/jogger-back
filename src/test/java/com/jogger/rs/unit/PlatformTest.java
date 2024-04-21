package com.jogger.rs.unit;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatformTest {

    Platform platform;

    @BeforeEach
    void setUp() throws Exception {
        platform = new Platform();
    }

    @AfterEach
    void tearDown() throws Exception {
        platform = null;
    }

    @Test
    @DisplayName("PlatformTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Platform.class, platform);
    }

    @Test
    @DisplayName("PlatformTest - setKeyPlatform")
    void setKeyPlatform() {
        platform.setKeyPlatform(1);
        assertEquals(platform.getKeyPlatform(), 1);
    }

    @Test
    @DisplayName("PlatformTest - setName")
    void setName() {
        platform.setName(PlatformName.Coursera);
        assertEquals(platform.getName(), PlatformName.Coursera);
    }

    @Test
    @DisplayName("PlatformTest - setDescription")
    void setDescription() {
        platform.setDescription("platform description");
        assertEquals(platform.getDescription(), "platform description");
    }

    @Test
    @DisplayName("PlatformTest - toString")
    void toStringLevel() {
        platform.setKeyPlatform(1);
        platform.setDescription("platform description");
        platform.setName(PlatformName.Coursera);
        assertTrue(platform.toString().contains(String.valueOf(1)));
        assertTrue(platform.toString().contains("platform description"));
        assertTrue(platform.toString().contains(PlatformName.Coursera.toString()));
    }
}
