package com.example.project_spring_boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_spring_boot.entity.Bid;
import com.example.project_spring_boot.service.BidService;

@RestController
public class BidController {
    @Autowired
    BidService bidService;
    
    @GetMapping("/bid")
    public ResponseEntity<List<Bid>>  getBidder() {
        return new ResponseEntity<>(bidService.getOffer(), HttpStatus.OK);        
    }

    @GetMapping("/bid/filter")
    public ResponseEntity<List<Bid>> getBidByAuctionEvent(@RequestParam(required = true) Long auctioneventId) {
        return new ResponseEntity<>(bidService.findBidByAuctionEvent(auctioneventId), HttpStatus.OK);        
    }

    @PostMapping("/bid")
    public ResponseEntity<Bid> createOffer(@RequestBody Bid offer,
        @RequestParam(required = true) Long auctioneventId,
        @RequestParam(required = true) Long bidderId)  {
        return new ResponseEntity<>(bidService.createOffer(offer, auctioneventId, bidderId), HttpStatus.CREATED);
    }

}
