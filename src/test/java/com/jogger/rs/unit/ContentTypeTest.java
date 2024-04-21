package com.jogger.rs.unit;

import com.jogger.rs.learning.materials.entities.ContentType;
import com.jogger.rs.learning.materials.entities.ContentTypeName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContentTypeTest {


    ContentType contentType;

    @BeforeEach
    void setUp() throws Exception {
        contentType = new ContentType();
    }

    @AfterEach
    void tearDown() throws Exception {
        contentType = null;
    }

    @Test
    @DisplayName("ContentTypeTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(ContentType.class, contentType);
    }

    @Test
    @DisplayName("ContentTypeTest - setKeyContentType")
    void setKeyContentType() {
        contentType.setKeyContentType(1);
        assertEquals(contentType.getKeyContentType(), 1);
    }

    @Test
    @DisplayName("ContentTypeTest - setName")
    void setName() {
        contentType.setName(ContentTypeName.Audio);
        assertEquals(contentType.getName(), ContentTypeName.Audio);
    }

    @Test
    @DisplayName("ContentTypeTest - setDescription")
    void setDescription() {
        contentType.setDescription("contentType description");
        assertEquals(contentType.getDescription(), "contentType description");
    }

    @Test
    @DisplayName("ContentTypeTest - toString")
    void toStringContentType() {
        contentType.setKeyContentType(1);
        contentType.setDescription("contentType description");
        contentType.setName(ContentTypeName.Audio);
        assertTrue(contentType.toString().contains(String.valueOf(1)));
        assertTrue(contentType.toString().contains("contentType description"));
        assertTrue(contentType.toString().contains(ContentTypeName.Audio.toString()));
    }
}
