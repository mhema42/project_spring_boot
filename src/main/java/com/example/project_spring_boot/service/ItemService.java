package com.example.project_spring_boot.service;

import java.util.List;

import com.example.project_spring_boot.entity.Item;

public interface ItemService {
    Item getItem(Long id);
    Item createItem(Item item);
    List<Item> getItemByUserId (Long id);
    List<Item> getItems();
}
