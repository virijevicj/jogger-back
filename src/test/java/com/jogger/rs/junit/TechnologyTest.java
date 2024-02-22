package com.jogger.rs.junit;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TechnologyTest {

    Technology technology;

    @BeforeEach
    void setUp() throws Exception {
        technology = new Technology();
    }

    @AfterEach
    void tearDown() throws Exception {
        technology = null;
    }

    @Test
    @DisplayName("TechnologyTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Technology.class, technology);
    }

    @Test
    @DisplayName("TechnologyTest - setKeyTechnology")
    void setKeyLevel() {
        technology.setKeyTechnology(1);
        assertEquals(technology.getKeyTechnology(), 1);
    }

    @Test
    @DisplayName("TechnologyTest - setName")
    void setName() {
        technology.setName(TechnologyName.Git);
        assertEquals(technology.getName(), TechnologyName.Git);
    }

    @Test
    @DisplayName("TechnologyTest - setDescription")
    void setDescription() {
        technology.setDescription("technology description");
        assertEquals(technology.getDescription(), "technology description");
    }

    @Test
    @DisplayName("TechnologyTest - toString")
    void toStringTechnology() {
        technology.setKeyTechnology(1);
        technology.setDescription("technology description");
        technology.setName(TechnologyName.Git);
        assertTrue(technology.toString().contains(String.valueOf(1)));
        assertTrue(technology.toString().contains("technology description"));
        assertTrue(technology.toString().contains(TechnologyName.Git.toString()));
    }
}
