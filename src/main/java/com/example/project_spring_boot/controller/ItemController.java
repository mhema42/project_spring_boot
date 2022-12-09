package com.example.project_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
     public ResponseEntity<Item> saveCar(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
     }
    
}
