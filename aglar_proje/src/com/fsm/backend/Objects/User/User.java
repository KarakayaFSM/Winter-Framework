package com.fsm.backend.Objects.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Interfaces.MyObject;
import com.fsm.backend.Interfaces.Repository;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unused"})
public class User implements MyObject {

    private UUID id;
    private String userName;
    private String password;
    private String mail;
    private int currency;
    private int limit;
    private int increaseRate = 0;

    public static Repository<User> repository = UserRepository.getInstance();
    private final List<Valuable> itemCollection = new ArrayList<>();

    @JsonCreator
    public User(@JsonProperty("credentials") Credentials credentials) {
        setId(credentials.getId());
        this.userName = credentials.getUserName();
        this.password = credentials.getPassword();
        this.currency = credentials.getCurrency();
        this.limit = currency;
        this.mail = credentials.getMail();
    }

    private void setId(UUID id) {
        this.id = id == null ? UUID.randomUUID() : id;
    }

    public void buy(Valuable valuable, int lastPrice) {
        itemCollection.add(valuable);
        currency -= lastPrice;
    }

    public static String getUserNameById(String id) {
        return repository.findById(UUID.fromString(id)).userName;
    }

    public static String getUserNameById(UUID id) {
        return repository.findById(id).userName;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", currency=" + currency +
                ", limit=" + limit +
                ", increaseRate=" + increaseRate +
                '}';
    }
}
