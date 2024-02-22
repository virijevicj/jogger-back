package com.jogger.rs.junit;

import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.learning.materials.entities.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Klasa u kojoj pisemo testove za klasu LearninMaterial
 *
 * @author Jovan Virijevic
 */

public class LearningMaterialTest {

    LearningMaterial lm;

    @BeforeEach
    void setUp() throws Exception {
        lm = new LearningMaterial();
    }

    @AfterEach
    void tearDown() throws Exception {
        lm = null;
    }

    @Test
    @DisplayName("LearningMaterialTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(LearningMaterial.class, lm);
    }

    @Test
    @DisplayName("LearninMaterialTest - setDescription")
    void setDescription() {
        lm.setDescription("description");
        assertEquals(lm.getDescription(), "description");
    }

    @Test
    @DisplayName("LearninMaterialTest - setLink")
    void setLink() {
        lm.setLink("link");
        assertEquals(lm.getLink(), "link");
    }

    @Test
    @DisplayName("LearninMaterialTest - setActive")
    void setActive() {
        lm.setActive(true);
        assertTrue(lm.getActive());
    }

    @Test
    @DisplayName("LearninMaterialTest - setArea")
    void setArea() {
        Area area = new Area();
        area.setKeyarea(1);
        area.setName(AreaName.Administracija);
        area.setDescription("Administracija");
        lm.setArea(area);
        assertNotNull(lm.getArea());
        assertEquals(lm.getArea().getKeyarea(), 1);
        assertEquals(lm.getArea().getName(), AreaName.Administracija);
        assertEquals(lm.getArea().getDescription(), "Administracija");
    }

    @Test
    @DisplayName("LearninMaterialTest - setContentType")
    void setContentType() {
        ContentType contentType = new ContentType();
        contentType.setKeyContentType(1);
        contentType.setName(ContentTypeName.Audio);
        contentType.setDescription("Audio");
        lm.setContentType(contentType);
        assertNotNull(lm.getContentType());
        assertEquals(lm.getContentType().getKeyContentType(), 1);
        assertEquals(lm.getContentType().getName(), ContentTypeName.Audio);
        assertEquals(lm.getContentType().getDescription(), "Audio");
    }

    @Test
    @DisplayName("LearninMaterialTest - setLevel")
    void setLevel() {
        Level level = new Level();
        level.setKeyLevel(1);
        level.setName(LevelName.Napredni);
        level.setDescription("Napredni");
        lm.setLevel(level);
        assertNotNull(lm.getLevel());
        assertEquals(lm.getLevel().getKeyLevel(), 1);
        assertEquals(lm.getLevel().getName(), LevelName.Napredni);
        assertEquals(lm.getLevel().getDescription(), "Napredni");
    }

    @Test
    @DisplayName("LearninMaterialTest - setPlatform")
    void setPlatform() {
        Platform platform = new Platform();
        platform.setKeyPlatform(1);
        platform.setName(PlatformName.Coursera);
        platform.setDescription("Coursera");
        lm.setPlatform(platform);
        assertNotNull(lm.getPlatform());
        assertEquals(lm.getPlatform().getKeyPlatform(), 1);
        assertEquals(lm.getPlatform().getName(), PlatformName.Coursera);
        assertEquals(lm.getPlatform().getDescription(), "Coursera");
    }

    @Test
    @DisplayName("LearninMaterialTest - setTechnology")
    void setTechnology() {
        Technology technology = new Technology();
        technology.setKeyTechnology(1);
        technology.setName(TechnologyName.Git);
        technology.setDescription("Git");
        lm.setTechnology(technology);
        assertNotNull(lm.getTechnology());
        assertEquals(lm.getTechnology().getKeyTechnology(), 1);
        assertEquals(lm.getTechnology().getName(), TechnologyName.Git);
        assertEquals(lm.getTechnology().getDescription(), "Git");
    }

    @Test
    @DisplayName("LearninMaterialTest - setComments")
    void setComments() {
        Comment comment1 = new Comment();
        comment1.setGrade(10);
        comment1.setText("test komentar 1");
        Comment comment2 = new Comment();
        comment2.setGrade(5);
        comment1.setText("test komentar 2");
        lm.setComments(List.of(comment1, comment2));
        assertEquals(2, lm.getComments().size());
    }

    @Test
    @DisplayName("LearninMaterialTest - toStringLM")
    void toStringLM() {
        lm.setLink("link");
        lm.setDescription("description");
        lm.setActive(false);
        assertTrue(lm.toString().contains("link"));
        assertTrue(lm.toString().contains("description"));
        assertTrue(lm.toString().contains("false"));
    }

}
