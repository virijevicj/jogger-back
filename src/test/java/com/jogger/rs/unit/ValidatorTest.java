package com.jogger.rs.unit;

import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.dto.UserDto;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.role.RoleName;
import com.jogger.rs.utils.Validator;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    Validator validator;

    @BeforeEach
    void setUp() throws Exception {
        validator = new Validator();
    }

    @AfterEach
    void tearDown() throws Exception {
        validator = null;
    }

    @Test
    @DisplayName("ValidatorTest - nevalidni username (prazan string)")
    @Order(1)
    void testEmptyUsername() {
        assertFalse(validator.validateUsername(""));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidni username (null)")
    @Order(2)
    void testNullUsername() {
        assertFalse(validator.validateUsername(null));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidni username (manje od 6 karaktera)")
    @Order(3)
    void testUsernameShorterThan6Characters() {
        assertFalse(validator.validateUsername("usern"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidni username (nema veliko slovo)")
    @Order(4)
    void testUsernameWithoutUppercaseCharacter() {
        assertFalse(validator.validateUsername("username1"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidni username (nema broj)")
    @Order(5)
    void testUsernameWithoutDigit() {
        assertFalse(validator.validateUsername("Username"));
    }

    @Test
    @DisplayName("ValidatorTest - validan username (ispunjava uslove)")
    @Order(6)
    void testValidUsername() {
        assertTrue(validator.validateUsername("Virke9"));
    }


    // password
    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (prazan string)")
    @Order(7)
    void testEmptyPassword() {
        assertFalse(validator.validatePassword(""));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (null)")
    @Order(8)
    void testNullPassword() {
        assertFalse(validator.validatePassword(null));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (manje od 10 karaktera)")
    @Order(9)
    void testPasswordShorterThan10Characters() {
        assertFalse(validator.validatePassword("password"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (nema veliko slovo)")
    @Order(10)
    void testPasswordWithoutUppercaseCharacter() {
        assertFalse(validator.validatePassword("password123"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (nema broj)")
    @Order(11)
    void testPasswordWithoutDigit() {
        assertFalse(validator.validatePassword("Passworddd"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (preko 20 karaktera)")
    @Order(12)
    void testPasswordWithMoreThan20Characters() {
        assertFalse(validator.validatePassword("preko20karakterasifra"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidna lozinka (nema specijalni karakter: !, ?, a, _, -)")
    @Order(13)
    void testPasswordWithoutSpecialCharacter() {
        assertFalse(validator.validatePassword("Passworddd"));
    }

    @Test
    @DisplayName("ValidatorTest - validna lozinka (ispunjava uslove)")
    @Order(14)
    void testValidPassword() {
        assertTrue(validator.validatePassword("Password123!"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan email (prazan string)")
    @Order(15)
    void validateEmptyEmail() {
        assertFalse(validator.validateEmail(""));
    }

    @Order(16)
    @Test
    @DisplayName("ValidatorTest - nevalidan email (null)")
    void validateNullEmail() {
        assertFalse(validator.validateEmail(null));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan email(ne zavrsava se sa gmail.com)")
    void validateEmailNotEndingWithCorrectly() {
        assertFalse(validator.validateEmail("test@hotmail.com"));
    }

    @Test
    @DisplayName("ValidatorTest - validan email")
    void validateEmail() {
        assertTrue(validator.validateEmail("test@gmail.com"));
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 1)")
    void validateNewUserBadRequest1() {
        UserDto user = new UserDto();
        user.setUsername("");
        user.setPassword("");
        user.setFirstName("");
        user.setLastName("");
        user.setEmail("");
        user.setRoles(null);
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 2)")
    void validateNewUserBadRequest2() {
        UserDto user = new UserDto();
        user.setUsername(""); // fali jedino username
        user.setPassword("Password123!");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@gmail.com");
        user.setRoles(List.of(RoleName.Admin.name()));
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 3)")
    void validateNewUserBadRequest3() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword(""); // fali jedino lozinka
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@gmail.com");
        user.setRoles(List.of(RoleName.Admin.name()));
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 4)")
    void validateNewUserBadRequest4() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setFirstName(""); // fali jedino ime
        user.setLastName("Test");
        user.setEmail("test@gmail.com");
        user.setRoles(List.of(RoleName.Admin.name()));
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 5)")
    void validateNewUserBadRequest5() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setFirstName("Test");
        user.setLastName(""); // fali jedino prezime
        user.setEmail("test@gmail.com");
        user.setRoles(List.of(RoleName.Admin.name()));
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 6)")
    void validateNewUserBadRequest6() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail(""); // fali jedino email
        user.setRoles(List.of(RoleName.Admin.name()));
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi user (primer 7)")
    void validateNewUserBadRequest7() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@gmail");
        user.setRoles(Collections.emptyList()); // fale jedino uloge
        assertNotEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - validan novi user")
    void validateNewUser() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@gmail.com");
        user.setRoles(List.of(RoleName.Admin.name()));
        assertEquals(validator.validateNewUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidno azuriranje user-a (primer 1)")
    void validateEditedUserBadRequest1() {
        UserDto user = new UserDto();
        user.setUsername("");
        user.setPassword("");
        user.setEmail("");
        assertNotEquals(validator.validateEditedUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidno azuriranje user-a (primer 2)")
    void validateEditedUserBadRequest2() {
        UserDto user = new UserDto();
        user.setUsername("LosUsername");
        user.setPassword("Password123!");
        user.setEmail("test@gmail.com");
        assertNotEquals(validator.validateEditedUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidno azuriranje user-a (primer 3)")
    void validateEditedUserBadRequest3() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("LosaSifra");
        user.setEmail("test@gmail.com");
        assertNotEquals(validator.validateEditedUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidno azuriranje user-a (primer 1)")
    void validateEditedUserBadRequest4() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setEmail("test@losemail.com");
        assertNotEquals(validator.validateEditedUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - validno azuriranje user-a ")
    void validateEditedUser() {
        UserDto user = new UserDto();
        user.setUsername("Username1");
        user.setPassword("Password123!");
        user.setEmail("test@gmail.com");
        assertEquals(validator.validateEditedUser(user), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi komentar (primer 1)")
    void validateNewCommentBadRequest1() {
        NewCommentDto newComment = new NewCommentDto();
        newComment.setText("");
        newComment.setGrade(0);
        assertNotEquals(validator.validateNewComment(newComment), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi komentar (primer 2)")
    void validateNewCommentBadRequest2() {
        NewCommentDto newComment = new NewCommentDto();
        newComment.setText(null);
        newComment.setGrade(0);
        assertNotEquals(validator.validateNewComment(newComment), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi komentar (primer 3)")
    void validateNewCommentBadRequest3() {
        NewCommentDto newComment = new NewCommentDto();
        newComment.setText("test komentar");
        newComment.setGrade(20);
        assertNotEquals(validator.validateNewComment(newComment), "");
    }

    @Test
    @DisplayName("ValidatorTest - validan novi komentar")
    void validateNewComment() {
        NewCommentDto newComment = new NewCommentDto();
        newComment.setText("test komentar");
        newComment.setGrade(10);
        assertEquals(validator.validateNewComment(newComment), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 1)")
    void validateNewLMBadRequest1() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("");
        newLM.setLink("");
        newLM.setArea(null);
        newLM.setPlatform(null);
        newLM.setContentType(null);
        newLM.setTechnology(null);
        newLM.setLevel(null);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 2)")
    void validateNewLMBadRequest2() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription(""); // jedino fali opis
        newLM.setLink("test_link");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 3)")
    void validateNewLMBadRequest3() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink(""); // jedino fali link
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 4)")
    void validateNewLMBadRequest4() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test_link");
        newLM.setArea(null); // jedino fali oblast
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 5)")
    void validateNewLMBadRequest5() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test link");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(null); // fali jedino platforma
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 6)")
    void validateNewLMBadRequest6() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(null); // jedino fali tip sadrzaja
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 7)")
    void validateNewLMBadRequest7() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(null); // jedino fali tehnologija
        newLM.setLevel(LevelName.Pocetni);
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - nevalidan novi materijal za ucenje (primer 8)")
    void validateNewLMBadRequest8() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(null); // jedino fali level
        assertNotEquals(validator.validateNewLM(newLM), "");
    }

    @Test
    @DisplayName("ValidatorTest - validan novi materijal za ucenje")
    void validateNewLMBadRequest() {
        NewLMDto newLM = new NewLMDto();
        newLM.setDescription("test_opis");
        newLM.setLink("test");
        newLM.setArea(AreaName.Backend);
        newLM.setPlatform(PlatformName.Google);
        newLM.setContentType(ContentTypeName.Audio);
        newLM.setTechnology(TechnologyName.Java);
        newLM.setLevel(LevelName.Pocetni);
        assertEquals(validator.validateNewLM(newLM), "");
    }

}
