package com.example.project_spring_boot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.project_spring_boot.entity.AuctionEvent;

import jakarta.transaction.Transactional;

public interface AuctionEventRepository extends CrudRepository<AuctionEvent, Long> {

    @Query("""
        SELECT DISTINCT a FROM AuctionEvent a LEFT JOIN FETCH a.bids bids 
        WHERE a.active = :active
        """)  
    List<AuctionEvent> filterByActive(@Param("active") Boolean active);

    @Query("""
        SELECT DISTINCT a FROM AuctionEvent a LEFT JOIN FETCH a.bids bids
        WHERE a.item.owner.id = :userId AND a.active = :active    
        """)  
    List<AuctionEvent> filterByUserAndActive(@Param("userId") Long userId, @Param("active") Boolean active);

    @Transactional
    @Modifying
    @Query("UPDATE AuctionEvent a SET a.active = :active WHERE a.stopTime < :now")
        void updateActive(@Param("active") Boolean status, @Param("now") LocalDateTime now);  
}
