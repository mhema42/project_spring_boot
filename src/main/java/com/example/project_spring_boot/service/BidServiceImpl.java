package com.example.project_spring_boot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.AuctionEvent;
import com.example.project_spring_boot.entity.Bid;
import com.example.project_spring_boot.entity.User;
import com.example.project_spring_boot.repository.AuctionEventRepository;
import com.example.project_spring_boot.repository.BidRepository;
import com.example.project_spring_boot.repository.UserRepository;

@Service
public class BidServiceImpl implements BidService {
    @Autowired
    BidRepository bidRepository;

    @Autowired
    AuctionEventRepository auctionEventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Bid> getOffer() {        
        return(List<Bid>)bidRepository.findAll();
    }

    @Override
    public List<Bid> findBidByAuctionEvent(Long auctioneventId) { 
        return bidRepository.filterByAuctionEventId(auctioneventId);
    }

    @Override
    public Bid createOffer(Bid offer, Long auctioneventId, Long bidderId) {  
        AuctionEvent auctionEvent = auctionEventRepository.findById(auctioneventId).orElse(null);
        User user = userRepository.findById(bidderId).orElse(null);  
        if(auctionEvent.getHighestBid() == null || offer.getOffer() > auctionEvent.getHighestBid()) {
            offer.setAuctionEvent(auctionEvent);
            offer.setBidder(user);
            offer.setBidTime(LocalDateTime.now());
            auctionEvent.setHighestBid(offer.getOffer());
            auctionEventRepository.save(auctionEvent);
            
            return bidRepository.save(offer);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

}