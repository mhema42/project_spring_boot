package com.example.project_spring_boot.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.ItemRepository;
import com.example.project_spring_boot.repository.UserRepository;

import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Item getItem(Long id) {
        if(itemRepository.findById(id).isPresent()) {
            return itemRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

    @Override
    public Item createItem(String name, String description, Long userId, MultipartFile file) {
        if(userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            Item item = new Item();
            item.setOwner(user);

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            if(filename.contains("..")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
            }
            try {
                item.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            item.setName(name);
            item.setDescription(description);

            return itemRepository.save(item);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>)itemRepository.findAll();
    }
     
    @Override
    public List<Item> getItemsByUserId(Long id) {
        if(userRepository.findById(id).isPresent()) {
            return (List<Item>)itemRepository.findByUser(id);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

}
