package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.AuctionRepository;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
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

    @Action(path = "createAuction", type = TYPE.POST)
    public Auction createAuction(@QueryParam(type = Valuable.class) Valuable valuable) {
        Auction auction = new Auction(valuable);
        AuctionRepository.addAuction(auction);
        return auction;
    }

    @Action(path = "updatePrice", type = TYPE.POST)
    public int updatePrice(@QueryParam(type = Bid.class) Bid bid) {
        return AuctionRepository
                .findByID(bid.getAuctionId())
                .apply(bid);
    }

}
