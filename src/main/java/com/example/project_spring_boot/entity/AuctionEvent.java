package com.example.project_spring_boot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class AuctionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private Boolean status; 

    @ManyToOne
    private Item item;

    // @ManyToOne
    // private User user; // add Getters + Setters if needed 

    public AuctionEvent() {  // Add params later 
       
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
}
