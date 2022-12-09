package com.example.project_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Item getUser(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item createUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

}    