package com.example.project_spring_boot.service;

import java.util.List;

import com.example.project_spring_boot.entity.AuctionEvent;

public interface AuctionEventService {

    AuctionEvent getAuctionEvent(Long id);
    List<AuctionEvent> getAuctionEvents();
    // newAuctionEvent
    
}
