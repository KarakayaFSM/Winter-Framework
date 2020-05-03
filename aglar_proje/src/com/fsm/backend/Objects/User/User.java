package com.fsm.backend.Objects.User;

import java.util.UUID;

public class User {

    private UUID id;
    private String userName;
    private String password;
    private String mail;
    private int currency;
    private int limit;
    private int increaseRate;

    public User(String userName, String password, int currency) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.password = password;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public int getCurrency() {
        return currency;
    }

    public int getLimit() {
        return limit;
    }

    public int getIncreaseRate() {
        return increaseRate;
    }
}
