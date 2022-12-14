package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.service.ItemService;

@RestController
public class ItemController {
    @Autowired 
    ItemService itemService;

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getItem(id), HttpStatus.OK);
    }

    @GetMapping("/item")
    public ResponseEntity <List<Item>> getItems() {
        return new ResponseEntity<>(itemService.getItems(), HttpStatus.OK);
    }

     @PostMapping("/item")
     public ResponseEntity<Item> saveItem(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("userId") Long userId, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(itemService.createItem(name, description, userId, file), HttpStatus.CREATED);
     }
}
