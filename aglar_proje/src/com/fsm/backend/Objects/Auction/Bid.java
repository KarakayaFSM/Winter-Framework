package com.fsm.backend.Objects.Auction;

import java.util.UUID;

public class Bid {
    private UUID userId;
    private int howMuch;

    public Bid(UUID userId, int howMuch) {
        this.userId = userId;
        this.howMuch = howMuch;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getHowMuch() {
        return howMuch;
    }
}
