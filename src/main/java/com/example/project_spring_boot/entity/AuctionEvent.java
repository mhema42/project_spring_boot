package com.example.project_spring_boot.entity;

import java.time.LocalDateTime;
// import java.util.Set;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class AuctionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private Boolean active; 

    @OneToOne
    private Item item;

    @OneToMany //(mappedBy = "auctionEvent", cascade = CascadeType.PERSIST) 
    private Set<Bid> bid;

    public AuctionEvent() {

    }

    public AuctionEvent(LocalDateTime startTime, LocalDateTime stopTime, Boolean active, Item item) { 
       this.startTime = startTime;
       this.stopTime = stopTime; 
       this.active = active; 
       this.item = item; 
    }

    public AuctionEvent(long id, LocalDateTime startTime, LocalDateTime stopTime, boolean active, Item item) {
        this.id = id;
        this.startTime = startTime;
        this.stopTime = stopTime; 
        this.active = active; 
        this.item = item; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    } 

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
