package com.example.project_spring_boot.service;

import java.util.List;

import com.example.project_spring_boot.entity.AuctionEvent;

public interface AuctionEventService {

    AuctionEvent getAuctionEvent(Long id);
    List<AuctionEvent> getAuctionEvents();
    List<AuctionEvent> getFilteredAuctionEvents(Boolean active); 
    List<AuctionEvent> getFilteredByUserAndActive(Long userId, Boolean active); 
    AuctionEvent newAuctionEvent(AuctionEvent auctionEvent, Long itemId);
    
}
