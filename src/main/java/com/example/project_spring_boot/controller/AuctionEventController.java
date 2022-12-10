package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_spring_boot.entity.AuctionEvent;
import com.example.project_spring_boot.service.AuctionEventService;

@RestController
public class AuctionEventController {

    @Autowired
    AuctionEventService auctionEventService;
    
    @GetMapping("/auctionevent/{id}")
    public ResponseEntity<AuctionEvent> getAuctionEvent(@PathVariable Long id) {
        return new ResponseEntity<>(auctionEventService.getAuctionEvent(id), HttpStatus.OK);
    }

    @GetMapping("/auctionevent")
    public ResponseEntity <List<AuctionEvent>> getAuctionEvents() {
        return new ResponseEntity<>(auctionEventService.getAuctionEvents(), HttpStatus.OK);
    }

}
