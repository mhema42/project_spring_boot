package com.example.project_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_spring_boot.service.AuctionEventService;

@RestController
public class AuctionEventController {

    @Autowired
    AuctionEventService auctionEventService;
    
}