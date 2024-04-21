package com.jogger.rs.unit;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AreaTest {

    Area area;

    @BeforeEach
    void setUp() throws Exception {
        area = new Area();
    }

    @AfterEach
    void tearDown() throws Exception {
        area = null;
    }

    @Test
    @DisplayName("AreaTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Area.class, area);
    }

    @Test
    @DisplayName("AreaTest - setKeyarea")
    void setKeyarea() {
        area.setKeyarea(1);
        assertEquals(area.getKeyarea(), 1);
    }

    @Test
    @DisplayName("AreaTest - setName")
    void setName() {
        area.setName(AreaName.Administracija);
        assertEquals(area.getName(), AreaName.Administracija);
    }

    @Test
    @DisplayName("AreaTest - setDescription")
    void setDescription() {
        area.setDescription("area description");
        assertEquals(area.getDescription(), "area description");
    }

    @Test
    @DisplayName("AreaTest - toString")
    void toStringArea() {
        area.setKeyarea(1);
        area.setDescription("area description");
        area.setName(AreaName.Administracija);
        assertTrue(area.toString().contains(String.valueOf(1)));
        assertTrue(area.toString().contains("area description"));
        assertTrue(area.toString().contains(AreaName.Administracija.toString()));
    }
}
