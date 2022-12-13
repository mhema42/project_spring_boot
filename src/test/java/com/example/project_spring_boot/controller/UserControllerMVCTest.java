package com.example.project_spring_boot.controller;

import org.junit.jupiter.api.Test;  
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerMVCTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void callingEndpointGetUsersShouldReturn200OK() throws Exception {
        mockMvc.perform( get("/user"))
        .andExpect(status().isOk());
    }

    @Test
    void getUsersShouldReturnListOfUsers() throws Exception{
        var user = new User();
        user.setName("Testuser");
        user.setId(1L);
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform( get("/user"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("Testuser"))
        .andExpect(jsonPath("$[0].id").value("1"));
        
    }
}