package com.jogger.rs.junit;

import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleTest {

    Role role;

    @BeforeEach
    void setUp() throws Exception {
        role = new Role();
    }

    @AfterEach
    void tearDown() throws Exception {
        role = null;
    }

    @Test
    @DisplayName("RoleTest - noArgsConstructor")
    void noArgsConstructor() {
        assertInstanceOf(Role.class, role);
    }

    @Test
    @DisplayName("RoleTest - setKeyRole")
    void setKeyRole() {
        role.setKeyRole(1);
        assertEquals(role.getKeyRole(), 1);
    }

    @Test
    @DisplayName("RoleTest - setName")
    void setName() {
        role.setName(RoleName.Intern);
        assertEquals(role.getName(), RoleName.Intern);
    }

    @Test
    @DisplayName("RoleTest - setDescription")
    void setDescription() {
        role.setDescription("role description");
        assertEquals(role.getDescription(), "role description");
    }

    @Test
    @DisplayName("RoleTest - toString")
    void toStringLevel() {
        role.setKeyRole(1);
        role.setDescription("role description");
        role.setName(RoleName.Intern);
        assertTrue(role.toString().contains(String.valueOf(1)));
        assertTrue(role.toString().contains("role description"));
        assertTrue(role.toString().contains(RoleName.Intern.toString()));
    }
}
