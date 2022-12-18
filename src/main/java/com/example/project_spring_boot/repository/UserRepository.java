package com.example.project_spring_boot.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.project_spring_boot.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByusername(String username);
}