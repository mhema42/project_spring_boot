package controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.project_spring_boot.controller.ItemController;

import com.example.project_spring_boot.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    
    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemController itemController;


}
