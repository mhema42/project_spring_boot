package com.example.project_spring_boot.controller;
    
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.UserRepository;
import com.example.project_spring_boot.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private List<User> userList;

    @BeforeEach
    void setup() {
        this.userList = new ArrayList<>();
        this.userList.add(new User(1L, "Mats", "user1", "pwd1", "mats@user.nu" ));
        this.userList.add(new User(1L, "Pelle", "user2", "pwd2", "pelle@user.nu" ));
        this.userList.add(new User(1L, "Karin", "user3", "pwd3", "karin@user.nu" ));
    }

/*     @Test
    void callingEndpointPostUserShouldReturn200OK() throws Exception {
        mockMvc.perform( post("/user"))
        .andExpect(status().isOk());
    } */

    @Test
    void callingEndpointGetUserShouldReturn200OK() throws Exception {
        mockMvc.perform( get("/user"))
        .andExpect(status().isOk());
    }

    @Test
    void callingEndpointGetUserIdShouldReturn200OK() throws Exception {
        mockMvc.perform( get("/user/1"))
        .andExpect(status().isOk());
    }

/*     @Test 
    void shouldFetchOneUserById() throws Exception {
        final Long userId = 1L;
        final User user = new User(1L, "Mats", "user1", "pwd1", "mats@user.nu" ));

        given(userService.getUser(userId)).willReturn(user);

        this.mockMvc.perform(get("/user/{id}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(user.getName())))
            .andExpect(jsonPath("$.username", is(user.g())));
    } */

    @Test
    void shouldFetchAllUsers() throws Exception {

        given(userService.getUsers()).willReturn(userList);
        
        this.mockMvc.perform(get("/user"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(userList.size())));
    }

}
