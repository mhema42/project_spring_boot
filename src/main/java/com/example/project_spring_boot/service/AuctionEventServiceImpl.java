package com.example.project_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.AuctionEvent;
import com.example.project_spring_boot.repository.AuctionEventRepository;

@Service
public class AuctionEventServiceImpl implements AuctionEventService {

    @Autowired
    AuctionEventRepository auctionEventRepository; 

    @Override
    public AuctionEvent getAuctionEvent(Long id) {
        return auctionEventRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"));
    }

    @Override
    public List<AuctionEvent> getAuctionEvents() {
        return (List<AuctionEvent>)auctionEventRepository.findAll();
    }

    // newAuctionEvent
    
}
