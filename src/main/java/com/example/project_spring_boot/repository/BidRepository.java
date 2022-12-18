package com.example.project_spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.project_spring_boot.entity.Bid;

public interface BidRepository extends CrudRepository<Bid, Long> {

    @Query("""
        SELECT b FROM Bid b WHERE b.auctionEvent.id = :id
    """)  
    List<Bid> filterByAuctionEventId(@Param("id") Long id);
    
}
