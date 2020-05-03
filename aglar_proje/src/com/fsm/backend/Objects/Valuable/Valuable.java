package com.fsm.backend.Objects.Valuable;

import java.util.UUID;

public class Valuable {

    private UUID id;
    private String name;
    private int initialPrice;

    public Valuable(String name, int initialPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.initialPrice = initialPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

}
