package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/auctionevent/active/{active}")
    public ResponseEntity <List<AuctionEvent>> getAuctionEventByStatus(@PathVariable Boolean active) {
        return new ResponseEntity<>(auctionEventService.getFilteredAuctionEvents(active), HttpStatus.OK);
    }

    @PostMapping("/auctionevent")
    public ResponseEntity<AuctionEvent> saveAuctionEvent(@RequestBody AuctionEvent auctionEvent, 
        @RequestParam(required = true) Long itemId) {
       
        return new ResponseEntity<>(auctionEventService.newAuctionEvent(auctionEvent, itemId), HttpStatus.CREATED);
    }

}
