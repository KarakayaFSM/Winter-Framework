package com.fsm.Utlis;

import com.fsm.Enums.TYPE;

import java.util.Map;

public class Request {

    private String action;
    private TYPE type;
    private Map<String, String> params;

    public Request(String action, String type, Map<String, String> params) {
        this.action = action;
        this.type = TYPE.valueOf(type);
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public TYPE getType() {
        return type;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
