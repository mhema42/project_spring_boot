package com.example.project_spring_boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner; 

    @OneToOne(mappedBy = "item", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private AuctionEvent auctionEvent;


    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
       this.owner = owner;
    }
    
}
