package com.example.project_spring_boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project_spring_boot.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {


}
