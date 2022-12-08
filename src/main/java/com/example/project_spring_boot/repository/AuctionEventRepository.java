package com.example.project_spring_boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project_spring_boot.entity.AuctionEvent;

public interface AuctionEventRepository extends CrudRepository<AuctionEvent, Long> {
    
}
