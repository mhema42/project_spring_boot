package com.example.project_spring_boot.controller;


import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
class ItemControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Item> itemList;


    @BeforeEach
    void setup() {
        this.itemList = new ArrayList<>();
        this.itemList.add(new Item(1L, "Super Nintendo", "Ett vanligt Super Nintedo"));
        this.itemList.add(new Item(2L, "En Ost", "En gammal ost"));
        this.itemList.add(new Item(3L, "En Skruv", "En gammal skruv"));
    }

    @Test
    void shouldFetchAllItems() throws Exception {

        given(itemService.getItems()).willReturn(itemList);
        
        this.mockMvc.perform(get("/item"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(itemList.size())));
    }

    @Test 
    void shouldFetchOneItemById() throws Exception {
        final Long itemId = 1L;
        final Item item = new Item(1L, "Super Nintendo", "Ett vanligt Super Nintedo");

        given(itemService.getItem(itemId)).willReturn(item);

        this.mockMvc.perform(get("/item/{id}", itemId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(item.getName())))
            .andExpect(jsonPath("$.description", is(item.getDescription())));
    }

    @Test
    void shouldCreateNewItem() throws Exception {
        given(itemService.createItem(any(Item.class))).willAnswer((Invocation -> Invocation.getArgument(0)));

        Item item = new Item(null, "Super Nintendo", "Ett vanligt Super Nintedo");

        this.mockMvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(item.getName())))
                .andExpect(jsonPath("$.description", is(item.getDescription())));

    }

   


    }

