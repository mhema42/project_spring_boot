package com.example.project_spring_boot.controller;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

// import static org.hamcrest.Matchers.hasSize;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.doNothing;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import com.example.project_spring_boot.entity.AuctionEvent;
import com.example.project_spring_boot.service.AuctionEventService;

@WebMvcTest(controllers = AuctionEventController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuctionEventControllerTest {

    @MockBean
    AuctionEventService auctionEventService;

    @Autowired
    private MockMvc mockMvc;

    private List<AuctionEvent> auctionEventList;       
    
    @BeforeEach
    void configureAuctionEventRepo() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime stopTime = startTime.plusDays(2);

        this.auctionEventList = new ArrayList<>();
        this.auctionEventList.add(new AuctionEvent(1L, startTime, stopTime, true, null));
        this.auctionEventList.add(new AuctionEvent(2L, startTime, stopTime, false, null));
    }

    @Test
    void shouldFetchAllAuctionEvents() throws Exception {
        given(auctionEventService.getAuctionEvents()).willReturn(auctionEventList);

        this.mockMvc.perform(get("/auctionevent"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(auctionEventList.size())));
    }
    
}
