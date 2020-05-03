package com.fsm.backend.Objects.Auction;

import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.*;

public class AuctionRepository {

    private static final List<Auction> auctions = Arrays.asList(
            new Auction(new Valuable("Antik Vazo", 100)),
            new Auction(new Valuable("Klasik Araba", 200)),
            new Auction(new Valuable("Vazo", 300))
    );

    public static List<Auction> getAllAuctions() {
        return new ArrayList<>(auctions);
    }

    public static Auction findByID(UUID id) {
        return auctions.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
