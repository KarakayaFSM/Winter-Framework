package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.Auction.Auction;

public class Message {

    private String senderName;
    private Event event;
    private Command command;
    private Auction auction; //used by auction created event
    private int newPrice; //used by price updated event

    @JsonCreator
    public Message(@JsonProperty("senderName") String senderName) {
        this.senderName = senderName;
    }

    public Message setEvent(Event event) {
        this.event = event;
        return this;
    }

    public String getSenderName() {
        return senderName;
    }

    public Event getEvent() {
        return event;
    }

    public Command getCommand() {
        return command;
    }

    public Auction getAuction() {
        return auction;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public Message setCommand(Command command) {
        this.command = command;
        return this;
    }

    Message setAuction(Auction auction) {
        //Triggered by auction created event
        this.auction = auction;
        return this;
    }

    Message setNewPrice(int newPrice) {
        //Triggered by price updated event
        this.newPrice = newPrice;
        return this;
    }

    public boolean isEvent() {
        return event != null;
    }

    public boolean isCommand() {
        return command != null;
    }
}
