package com.fsm.backend.Objects.Auction;

import java.util.ArrayList;
import java.util.UUID;


public class AuctionRepository {

    private static final ArrayList<Auction> auctions = new ArrayList<>();

    public static ArrayList<Auction> getAllAuctions() {
        return new ArrayList<>(auctions);
    }

    public static Auction findByID(UUID id) {
        return auctions.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public static Auction addAuction(Auction auction) {
        auctions.add(auction);
        return auction;
    }

}
