package com.fsm.backend.Objects.Auction;

import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Auction {

    private UUID id;
    private String name;
    private int currentPrice;

    List<Bid> bids = new ArrayList<>();

    public Auction(Valuable valuable) {
        this.id = UUID.randomUUID();
        System.out.println(id);
        this.name = valuable.getName() + " auction";
        currentPrice = valuable.getInitialPrice();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void increase(Bid bid) {
        currentPrice += bid.getHowMuch();
        bids.add(new Bid(bid.getUserId(), currentPrice));
    }

}
