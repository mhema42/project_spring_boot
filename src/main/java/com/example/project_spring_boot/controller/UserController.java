package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class UserController {

    Logger logger = LogManager.getLogger(UserController.class);

    @Autowired 
    UserService userService;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity <List<User>> getUsers() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("requested by user: " + auth.getName());

        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

     @PostMapping("/user")
     public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
     }
}