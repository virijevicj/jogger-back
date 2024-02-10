package com.jogger.rs.junit;

import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.dto.StandardResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.role.RoleName;
import com.jogger.rs.utils.ResponseFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseFactoryTest {

    ResponseFactory responseFactory;

    @BeforeEach
    void setUp() throws Exception {
        responseFactory = new ResponseFactory();
    }

    @AfterEach
    void tearDown() throws Exception {
        responseFactory = null;
    }

    @Test
    @DisplayName("ResponseFactoryTest - standardno kreiranje odgovora")
    void createResponse() {
        ResponseEntity<Object> response = responseFactory.createResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "data");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.OK.value());
        assertEquals(responseDto.getMessage(), HttpStatus.OK.getReasonPhrase());
        assert responseDto.getData() != null;
        assertEquals("data", String.valueOf(responseDto.getData()));
    }

    @Test
    @DisplayName("ResponseFactoryTest - neocekivani RuntimeException (bez poruke)")
    void somethingWentWrongWithoutMessage() {
        ResponseEntity<Object> response = responseFactory.somethingWentWrong();
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals(responseDto.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
        assert responseDto.getData() != null;
        assertEquals(ErrorMessage.SOMETHING_WENT_WRONG, String.valueOf(responseDto.getData()));
    }

    @Test
    @DisplayName("ResponseFactoryTest - neocekivani RuntimeException (bez poruke)")
    void somethingWentWrongWithMessage() {
        ResponseEntity<Object> response = responseFactory.somethingWentWrong("test");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertEquals(responseDto.getMessage(), "test");
        assert responseDto.getData() != null;
        assertEquals(ErrorMessage.SOMETHING_WENT_WRONG, String.valueOf(responseDto.getData()));
    }

    @Test
    @DisplayName("ResponseFactoryTest - ok poruka (sa prosledjenim podacima)")
    void okWithData() {
        LoginResponseDto loginResponseDto = LoginResponseDto
                .builder()
                .token("test-token")
                .roles(List.of(RoleName.Admin.name(), RoleName.Developer.name()))
                .build();
        ResponseEntity<Object> response = responseFactory.ok(loginResponseDto);
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.OK.value());
        assertEquals(responseDto.getMessage(), HttpStatus.OK.toString());
        assert responseDto.getData() != null;
        LoginResponseDto loginResponseDto2 = (LoginResponseDto) responseDto.getData();
        assertEquals(loginResponseDto.getToken(), loginResponseDto2.getToken());
        assert loginResponseDto2.getRoles() != null;
        assertTrue(loginResponseDto2.getRoles().contains(RoleName.Admin.name()));
        assertTrue(loginResponseDto2.getRoles().contains(RoleName.Developer.name()));
    }

    @Test
    @DisplayName("ResponseFactoryTest - ok poruka (sa prosledjenom porukom)")
    void okWithMessage() {
        ResponseEntity<Object> response = responseFactory.ok("test poruka");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.OK.value());
        assertEquals(responseDto.getMessage(), "test poruka");
        assertEquals(responseDto.getData(), "");
    }

    @Test
    @DisplayName("ResponseFactoryTest - ok poruka (sa prosledjenim podacima i prosledjenom porukom)")
    void okWithDataAndMessage() {
        LoginResponseDto loginResponseDto = LoginResponseDto
                .builder()
                .token("test-token")
                .roles(List.of(RoleName.Admin.name(), RoleName.Developer.name()))
                .build();
        ResponseEntity<Object> response = responseFactory.ok("test poruka", loginResponseDto);
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.OK.value());
        assertEquals(responseDto.getMessage(), "test poruka");
        assert responseDto.getData() != null;
        LoginResponseDto loginResponseDto2 = (LoginResponseDto) responseDto.getData();
        assertEquals(loginResponseDto.getToken(), loginResponseDto2.getToken());
        assert loginResponseDto2.getRoles() != null;
        assertTrue(loginResponseDto2.getRoles().contains(RoleName.Admin.name()));
        assertTrue(loginResponseDto2.getRoles().contains(RoleName.Developer.name()));
    }

    @Test
    @DisplayName("ResponseFactoryTest - bad request")
    void badRequest() {
        ResponseEntity<Object> response = responseFactory.badRequest("bad request");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.BAD_REQUEST.value());
        assertEquals(responseDto.getMessage(), "bad request");
        assertEquals(responseDto.getData(), "");
    }

    @Test
    @DisplayName("ResponseFactoryTest - not found")
    void notFound() {
        ResponseEntity<Object> response = responseFactory.notFound("not found");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.NOT_FOUND.value());
        assertEquals(responseDto.getMessage(), "not found");
        assertEquals(responseDto.getData(), "");
    }

    @Test
    @DisplayName("ResponseFactoryTest - forbidden")
    void forbidden() {
        ResponseEntity<Object> response = responseFactory.forbidden("forbidden");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.FORBIDDEN.value());
        assertEquals(responseDto.getMessage(), "forbidden");
        assertEquals(responseDto.getData(), "");
    }

    @Test
    @DisplayName("ResponseFactoryTest - unauthorized")
    void unauthorized() {
        ResponseEntity<Object> response = responseFactory.unauthorized("unauthorized");
        StandardResponseDto responseDto = (StandardResponseDto) response.getBody();
        assert responseDto != null;
        assertNotNull(responseDto.getTime());
        assertInstanceOf(LocalDateTime.class, responseDto.getTime());
        assertEquals(responseDto.getStatusCode(), HttpStatus.UNAUTHORIZED.value());
        assertEquals(responseDto.getMessage(), "unauthorized");
        assertEquals(responseDto.getData(), "");
    }
}
