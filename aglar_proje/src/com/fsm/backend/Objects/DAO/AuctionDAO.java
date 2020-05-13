package com.fsm.backend.Objects.DAO;

import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.UUID;

public class AuctionDAO {

    private UUID id;
    private String name;
    private int currentPrice;
    private UUID winnerId;
    private Valuable item;

    public AuctionDAO(UUID id,
                      String name, int currentPrice,
                      UUID winnerId, Valuable item) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.winnerId = winnerId;
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public UUID getWinnerId() {
        return winnerId;
    }

    public Valuable getItem() {
        return item;
    }

    public static AuctionDAO getDAOFromAuction(Auction auction) {
        return new AuctionDAO(auction.getId(),
                auction.getName(),
                auction.getCurrentPrice(),
                auction.getWinnerId(),
                auction.getItem());
    }

}
