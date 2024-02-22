package com.jogger.rs.junit;

import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {

    Comment comment;

    @BeforeEach
    void setUp() throws Exception {
        comment = new Comment();
    }

    @AfterEach
    void tearDown() throws Exception {
        comment = null;
    }

    @Test
    @DisplayName("CommentTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Comment.class, comment);
    }

    @Test
    @DisplayName("CommentTest - setKeyComment")
    void setKeyComment() {
       comment.setKeyComment(1);
       assertEquals(comment.getKeyComment(), 1);
    }

    @Test
    @DisplayName("CommentTest - setText")
    void setText() {
        comment.setText("comment text");
        assertEquals(comment.getText(), "comment text");
    }

    @Test
    @DisplayName("CommentTest - setGrade")
    void setGrade() {
        comment.setGrade(10);
        assertEquals(comment.getGrade(), 10);
    }

    @Test
    @DisplayName("CommentTest - setLm")
    void setLm() {
        LearningMaterial lm = new LearningMaterial();
        lm.setDescription("description");
        lm.setLink("link");
        // postavljamo oblast
        Area area = new Area();
        area.setKeyarea(1);
        area.setName(AreaName.Administracija);
        area.setDescription("Administracija");
        lm.setArea(area);

        // postavljamo tip sadrzaja
        ContentType contentType = new ContentType();
        contentType.setKeyContentType(1);
        contentType.setName(ContentTypeName.Audio);
        contentType.setDescription("Audio");
        lm.setContentType(contentType);

        // postavljamo nivo
        Level level = new Level();
        level.setKeyLevel(1);
        level.setName(LevelName.Napredni);
        level.setDescription("Napredni");
        lm.setLevel(level);

        // postavljamo platformu
        Platform platform = new Platform();
        platform.setKeyPlatform(1);
        platform.setName(PlatformName.Coursera);
        platform.setDescription("Coursera");
        lm.setPlatform(platform);

        // postavljamo tehnologiju
        Technology technology = new Technology();
        technology.setKeyTechnology(1);
        technology.setName(TechnologyName.Git);
        technology.setDescription("Git");
        lm.setTechnology(technology);

        comment.setLm(lm);
        LearningMaterial lm2 = comment.getLm();

        assertEquals(lm2.getDescription(), "description");
        assertEquals(lm2.getLink(), "link");
        assertNotNull(lm2.getArea());
        assertEquals(lm2.getArea().getKeyarea(), 1);
        assertEquals(lm2.getArea().getName(), AreaName.Administracija);
        assertEquals(lm2.getArea().getDescription(), "Administracija");
        assertNotNull(lm2.getContentType());
        assertEquals(lm2.getContentType().getKeyContentType(), 1);
        assertEquals(lm2.getContentType().getName(), ContentTypeName.Audio);
        assertEquals(lm2.getContentType().getDescription(), "Audio");
        assertNotNull(lm2.getLevel());
        assertEquals(lm2.getLevel().getKeyLevel(), 1);
        assertEquals(lm2.getLevel().getName(), LevelName.Napredni);
        assertEquals(lm2.getLevel().getDescription(), "Napredni");
        assertNotNull(lm2.getPlatform());
        assertEquals(lm2.getPlatform().getKeyPlatform(), 1);
        assertEquals(lm2.getPlatform().getName(), PlatformName.Coursera);
        assertEquals(lm2.getPlatform().getDescription(), "Coursera");
        assertNotNull(lm2.getTechnology());
        assertEquals(lm2.getTechnology().getKeyTechnology(), 1);
        assertEquals(lm2.getTechnology().getName(), TechnologyName.Git);
        assertEquals(lm2.getTechnology().getDescription(), "Git");
    }

    @Test
    @DisplayName("CommentTest - setUser")
    void setUser() {
        User user = new User();
        user.setUsername("Test username");
        user.setPassword("Test password");
        user.setFirstName("Test first name");
        user.setLastName("Test last name");
        user.setEmail("Test email");
        user.setActive(true);

        comment.setUser(user);
        User user2 = comment.getUser();
        assertEquals(user2.getUsername(), "Test username");
        assertEquals(user2.getPassword(), "Test password");
        assertEquals(user2.getFirstName(), "Test first name");
        assertEquals(user2.getLastName(), "Test last name");
        assertEquals(user2.getEmail(), "Test email");
        assertTrue(user2.getActive());
    }

    @Test
    @DisplayName("CommentTest - toString")
    void toStringComment() {
        comment.setKeyComment(1);
        comment.setText("text");
        comment.setGrade(7);
        assertTrue(comment.toString().contains(String.valueOf(1)));
        assertTrue(comment.toString().contains("text"));
        assertTrue(comment.toString().contains(String.valueOf(7)));
    }
}
