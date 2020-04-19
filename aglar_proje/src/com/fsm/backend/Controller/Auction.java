package com.fsm.backend.Controller;

public class Auction {

    private String name;
    private int id;

    public Auction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
