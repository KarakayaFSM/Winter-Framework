package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.User.User;

public class Event {
    String eventType;

    @JsonCreator
    public Event(@JsonProperty("eventType") String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public static Message getAuctionCreatedEvent(Auction auction) {
        return new Message("server")
                .setEvent(new Event("AUCTION_CREATED"))
                .setAuction(auction);
    }

    public static Message getPriceUpdatedEvent(Bid bid, int newPrice) {
        return new Message(User.getUserNameById(bid.getUserId()))
                .setEvent(new Event("PRICE_UPDATED"))
                .setNewPrice(newPrice);
    }
}
