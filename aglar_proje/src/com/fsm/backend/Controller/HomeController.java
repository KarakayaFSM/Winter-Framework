package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.AuctionRepository;

import java.util.List;
import java.util.UUID;

@Controller(path = "auctions")
public class HomeController implements MyHttpHandler {

    @Action(type = TYPE.GET)
    public String index() {
        return "Welcome to Auctions";
    }

    @Action(path = "getAllAuctions")
    public List<Auction> getAllAuctions() {
        return AuctionRepository.getAllAuctions();
    }

    @Action(path = "getAuctionById")
    public Auction getAuctionById(String id) {
        return AuctionRepository.findByID(UUID.fromString(id));
    }

}
