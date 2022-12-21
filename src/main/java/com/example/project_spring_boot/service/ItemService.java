package com.example.project_spring_boot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.project_spring_boot.entity.Item;

public interface ItemService {
    Item getItem(Long id);
    Item createItem(String name, String description, Long userId, MultipartFile file);
    List<Item> getItemsByUserId (Long id);
    List<Item> getItems();
}
