package com.jogger.rs.unit;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LevelTest {

    Level level;

    @BeforeEach
    void setUp() throws Exception {
        level = new Level();
    }

    @AfterEach
    void tearDown() throws Exception {
        level = null;
    }

    @Test
    @DisplayName("LevelTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Level.class, level);
    }

    @Test
    @DisplayName("LevelTest - setKeyLevel")
    void setKeyLevel() {
        level.setKeyLevel(1);
        assertEquals(level.getKeyLevel(), 1);
    }

    @Test
    @DisplayName("LevelTest - setName")
    void setName() {
        level.setName(LevelName.Napredni);
        assertEquals(level.getName(), LevelName.Napredni);
    }

    @Test
    @DisplayName("LevelTest - setDescription")
    void setDescription() {
        level.setDescription("level description");
        assertEquals(level.getDescription(), "level description");
    }

    @Test
    @DisplayName("LevelTest - toString")
    void toStringLevel() {
        level.setKeyLevel(1);
        level.setDescription("level description");
        level.setName(LevelName.Napredni);
        assertTrue(level.toString().contains(String.valueOf(1)));
        assertTrue(level.toString().contains("level description"));
        assertTrue(level.toString().contains(LevelName.Napredni.toString()));
    }
}
