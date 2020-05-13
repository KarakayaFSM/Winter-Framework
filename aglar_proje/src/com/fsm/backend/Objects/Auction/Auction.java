package com.fsm.backend.Objects.Auction;

import com.fsm.backend.Interfaces.MyObject;
import com.fsm.backend.Interfaces.Repository;
import com.fsm.backend.Objects.User.User;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SuppressWarnings("FieldCanBeLocal")
public class Auction implements MyObject {

    private UUID id;
    private String name;
    private int currentPrice;
    private UUID winnerId;
    private Valuable item;
    public static Repository<Auction> repository;
    List<Bid> bids = new ArrayList<>();

    public Auction(Valuable item) {
        this.id = UUID.randomUUID();
        this.name = item.getName() + " auction";
        this.item = item;
        currentPrice = item.getInitialPrice();
        repository = new AuctionRepository();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWinnerId() {
        return winnerId;
    }

    public Valuable getItem() {
        return item;
    }

    public int increasePrice(Bid bid) {
        currentPrice += bid.getHowMuch();
        winnerId = bid.getUserId();
        bids.add(new Bid(this.id, bid.getUserId(), currentPrice));
        return currentPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void sellToWinner() {
        User.repository.findById(winnerId).buy(item, currentPrice);
    }

}
