package com.example.project_spring_boot.service;

import java.util.List;

import com.example.project_spring_boot.entity.User;

public interface UserService {
    User getUser(Long id);
    User createUser(User user);
    List<User> getUsers();
    User getUser(String username);
}

