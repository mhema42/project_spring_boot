package com.example.project_spring_boot.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.ItemRepository;
import com.example.project_spring_boot.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;

    @Test
    public void getItemsFromRepoTest() {
        Mockito.when(itemRepository.findAll()).thenReturn(List.of(
            new Item("Antikt Svärd", "Gammalt trasigt svärd"),
            new Item("Super Nintendo", "Komplett 16-bits konsol")
            ));

            List<Item> result = itemServiceImpl.getItems();

            assertEquals("Antikt Svärd", result.get(0).getName());
            assertEquals("Super Nintendo", result.get(1).getName());
    }

    @Test
    public void getItemByIdFromRepoTest() {
        Item item = new Item(1L, "Super Nintendo", "Komplett 16-bits konsol");
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Long id = item.getId();
        Item result = itemServiceImpl.getItem(id);
        assertEquals(item, result);
    }

    @Test
    public void CreateItemTest() throws IOException {
        MockMultipartFile file= new MockMultipartFile("file", "photo.PNG", MediaType.IMAGE_PNG_VALUE, "photo".getBytes()); 

        User user = new User(1L, "Bosse", "LaserBosse", "password", "email.se");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        itemServiceImpl.createItem("Super Nintendo", "Komplett 16-bits konsol", 1L, file);

        
        verify(itemRepository, Mockito.times(1)).save(any(Item.class));
    }

    
}
