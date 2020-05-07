package com.fsm.backend.Objects.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Credentials {

    private UUID id;
    private String userName;
    private String password;
    private String mail;
    private int currency;

    @JsonCreator
    public Credentials(@JsonInclude(JsonInclude.Include.NON_NULL)
                       @JsonProperty("id") String id,
                       @JsonProperty("userName") String userName,
                       @JsonProperty("password") String password,
                       @JsonProperty("mail") String mail,
                       @JsonProperty("currency") int currency) {
        setId(id);
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id != null ? UUID.fromString(id) : null;
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
}
