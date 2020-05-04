package com.fsm.backend.Objects.Auction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Bid {

    private UUID auctionId;
    private UUID userId;
    private UUID bidId;
    private int howMuch;

    @JsonCreator
    public Bid(@JsonProperty("auctionId") String auctionId,
               @JsonProperty("userId") String userId,
               @JsonProperty("howMuch") int howMuch) {
        this.auctionId = UUID.fromString(auctionId);
        this.userId = UUID.fromString(userId);
        this.howMuch = howMuch;
        this.bidId = UUID.randomUUID();
    }

    public Bid(UUID auctionId, UUID userId, int howMuch) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.howMuch = howMuch;
        this.bidId = UUID.randomUUID();
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getHowMuch() {
        return howMuch;
    }

    public UUID getBidId() {
        return bidId;
    }
}
