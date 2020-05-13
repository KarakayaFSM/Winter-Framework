package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.Message.Event;
import com.fsm.backend.Objects.Message.Message;
import com.fsm.backend.Objects.Valuable.Valuable;
import com.fsm.backend.Utils.Broadcast;

import java.util.Collection;

@SuppressWarnings("unused")
@Controller(path = "auctions")
public class HomeController implements MyHttpHandler {

    //Once auction is started, other users cannot join.

    @Action(type = TYPE.GET)
    public String index() {
        return "Welcome to Auctions";
    }

    @Action(path = "createAuction", type = TYPE.POST)
    public Auction createAuction(@QueryParam(type = Valuable.class) Valuable valuable) {
        Auction auction = new Auction(valuable);
        Auction.repository.add(auction);
        Message auctionCreated = Event.getAuctionCreatedEvent(auction);
        Broadcast.broadCastMessage(auctionCreated);
        return auction;
    }

    @Action(path = "getAllAuctions")
    public Collection<Auction> getAllAuctions() {
        return Auction.repository.getAll();
    }

    @Action(path = "getAuctionById")
    public Auction getAuctionById(String id) {
        return Auction.repository.findById(id);
    }

    @Action(path = "updatePrice", type = TYPE.POST)
    public int updatePrice(@QueryParam(type = Bid.class) Bid bid) {
        int newPrice = increasePrice(bid);
        Broadcast.broadCastMessage(Event.
                getPriceUpdatedEvent(bid, newPrice));
        return newPrice;
    }

    private int increasePrice(Bid bid) {
        return Auction.repository
                .findById(bid.getAuctionId())
                .increasePrice(bid);
    }

}
