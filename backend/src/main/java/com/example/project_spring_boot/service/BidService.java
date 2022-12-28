package com.example.project_spring_boot.service;

import java.util.List;
import com.example.project_spring_boot.entity.Bid;

public interface BidService {
    
    Bid createOffer(Bid offer, Long auctioneventId, Long bidderId); 
    List<Bid> getOffer();
    List<Bid> findBidByAuctionEvent(Long auctioneventId);

}
