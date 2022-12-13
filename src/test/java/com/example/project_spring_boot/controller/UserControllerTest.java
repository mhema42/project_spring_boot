package com.example.project_spring_boot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
   
    @Autowired                           
    private MockMvc mockMvc;  

    @Autowired
    private ObjectMapper objectMapper;
                                                 
    @MockBean                           
    private UserService userService; 
                                               
    private List<User> userList;       
                                            
    @BeforeEach                           
    public void setUp() {                               
        this.userList = new ArrayList<>();                                    
        this.userList.add(new User(1L, "user1", "user1", "pwd1","user1@gmail.com"));                               
        this.userList.add(new User(2L, "user2", "user2", "pwd2","user2@gmail.com"));  
        this.userList.add(new User(3L, "user3", "user3", "pwd3","user3@gmail.com"));                                 
    }

    @Test
    public void shouldFetchAllUsers() throws Exception {

        given(userService.getUsers()).willReturn(userList);

        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }
}
