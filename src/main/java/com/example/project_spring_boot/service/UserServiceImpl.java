package com.example.project_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
    }

    @Override
    public User createUser(User user) {
        if (user.getEmail().contains("@")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
    }

    @Override
    public List<User> getUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByusername(username);
    }

}    