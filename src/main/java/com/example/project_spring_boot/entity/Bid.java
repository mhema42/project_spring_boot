package com.example.project_spring_boot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private double offer;
    private LocalDateTime bidTime;

 
    @ManyToOne
    private User bidder;

    @ManyToOne
    private AuctionEvent auctionEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    } 

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

     
    public LocalDateTime getBidTime() {
        return bidTime;
    }
   
    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }


}