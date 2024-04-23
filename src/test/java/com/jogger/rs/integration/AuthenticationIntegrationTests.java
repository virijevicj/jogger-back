package com.jogger.rs.integration;

import com.jogger.rs.labels.RequestMappingPrefix;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthenticationIntegrationTests {

    @Autowired
    private MockMvc mvc;

    private final static String TEST_ROUTE = RequestMappingPrefix.USER;

    private final static String TEST_ROUTE_FOR_DEVELOPER = RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES;

    private final static String WRONG_AUTH_TOKEN = "Test fdfdfsdfsdfsdfdsfsd.fdsfsdfsdfsdadfvfdafasa.ssaasfsafsdaf";

    private final static String NON_EXPIRED_JWT = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZXZlbG9wZXIxIiwiaWF0IjoxNzEzODgyMzU1fQ.WpsIFuBA7TOcu-foQPdguSqMXdBM0uJr50K8FarNVLU";

    private final static String EXPIRED_JWT = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEZXZlbG9wZXIxIiwiaWF0IjoxNzEzODgyNDMwLCJleHAiOjE3MTM4ODI0MzF9.XmZGCf72MU5kRETaU0e8t43cbPfCCATdC1UYsd2KD5c";
    @Test
    @Order(1)
    public void noAuthAddedInHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(TEST_ROUTE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void noAuthStartWithBearerAddedInHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(TEST_ROUTE)
                        .header(HttpHeaders.AUTHORIZATION, WRONG_AUTH_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Order(3)
    public void expiredJwtTokenAddedInHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(TEST_ROUTE)
                        .header(HttpHeaders.AUTHORIZATION, EXPIRED_JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @Order(4)
    public void nonExpiredJwtTokenAddedInHeaderForForbiddenRouteForDeveloperRole() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(TEST_ROUTE)
                        .header(HttpHeaders.AUTHORIZATION, NON_EXPIRED_JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    @Test
    @Order(5)
    public void nonExpiredJwtTokenAddedInHeader() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(TEST_ROUTE_FOR_DEVELOPER)
                        .header(HttpHeaders.AUTHORIZATION, NON_EXPIRED_JWT)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
