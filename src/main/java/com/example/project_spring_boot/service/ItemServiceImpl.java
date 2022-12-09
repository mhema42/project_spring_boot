package com.example.project_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItem(Long id) {
        if(itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>)itemRepository.findAll();
    }
    /* 
    @Override
    public List<Item> getItemByUserId(Long id) {
        return (List<Item>)itemRepository.findByUser(id);
    }
    */
}
