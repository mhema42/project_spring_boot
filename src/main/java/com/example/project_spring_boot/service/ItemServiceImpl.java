package com.example.project_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItem(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item createItem(Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Item> getItems() {
        // TODO Auto-generated method stub
        return null;
    }
}
