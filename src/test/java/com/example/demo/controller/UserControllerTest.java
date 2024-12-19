package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(value = UserControllerTest.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void signupWithEmail() throws Exception {

        mockMvc.perform(post("/users"));
    }

    @Test
    void loginWithEmail() throws Exception {
        mockMvc.perform(post("/users/login"));
    }
}