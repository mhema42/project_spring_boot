package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/item/getbyownerid")
    public ResponseEntity <List<Item>> getItemsByUserId(@RequestParam Long id) {
        return new ResponseEntity<>(itemService.getItemsByUserId(id), HttpStatus.OK);
    }

     @PostMapping("/item")
     public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
     }

     @PatchMapping("/item/addowner")
     public ResponseEntity<Item> addOwner(@RequestParam Long userId, @RequestParam Long itemId) {
        return new ResponseEntity<>(itemService.addOwner(userId, itemId), HttpStatus.OK);
     }
    
}
