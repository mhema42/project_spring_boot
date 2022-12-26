package com.example.project_spring_boot.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private double offer;
    private LocalDateTime bidTime;
    private double highestBid;

    public Bid() {

    }

    public Bid(double offer, LocalDateTime bidTime) {
        this.offer = offer; 
        this.bidTime = bidTime;
    }

    @ManyToOne
    private User bidder;

    @JsonIgnore
    @ManyToOne
    private AuctionEvent auctionEvent;

    /* @JsonIgnore
    @OneToOne
    private AuctionEvent HighestOffer; */

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

    public AuctionEvent getAuctionEvent() {
        return auctionEvent;
    }

    public void setAuctionEvent(AuctionEvent auctionEvent) {
        this.auctionEvent = auctionEvent;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }



}