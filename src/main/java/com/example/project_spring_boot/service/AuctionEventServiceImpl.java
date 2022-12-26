package com.example.project_spring_boot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.project_spring_boot.entity.AuctionEvent;
import com.example.project_spring_boot.entity.Bid;
import com.example.project_spring_boot.entity.Item;
import com.example.project_spring_boot.repository.AuctionEventRepository;

@Service
public class AuctionEventServiceImpl implements AuctionEventService {

    @Autowired
    AuctionEventRepository auctionEventRepository; 

    @Autowired
    ItemService itemService; 

    @Autowired
    BidService bidService; 

    @Override
    public AuctionEvent getAuctionEvent(Long id) {
        auctionEventRepository.updateActive(false, LocalDateTime.now());

        return auctionEventRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"));
    }

    @Override
    public List<AuctionEvent> getAuctionEvents() {
        auctionEventRepository.updateActive(false, LocalDateTime.now());

        return (List<AuctionEvent>)auctionEventRepository.findAll();
    }

    @Override
    public List<AuctionEvent> getFilteredAuctionEvents(Boolean active) {
        auctionEventRepository.updateActive(false, LocalDateTime.now());

        if(active != null) {
            return auctionEventRepository.filterByActive(active);

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"); 
    }

    @Override
    public AuctionEvent newAuctionEvent(AuctionEvent auctionEvent, Long itemId) {
        Item item = itemService.getItem(itemId);
/*         Bid getHighestBid = BidService.getHighestBid(auctionEventId); */
        auctionEvent.setItem(item);
        auctionEvent.setActive(true);
        auctionEvent.setStartTime(LocalDateTime.now());
        

        if(auctionEvent.getStopTime().isAfter(auctionEvent.getStartTime())) {
            return auctionEventRepository.save(auctionEvent);
        } 
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request"); 
    }

}
