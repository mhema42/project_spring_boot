package com.example.project_spring_boot.service;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.entity.User;

public interface UserService {
    Item getUser(Long id);
    Item createUser(User user);

}

