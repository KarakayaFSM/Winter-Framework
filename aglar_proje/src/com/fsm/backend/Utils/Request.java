package com.fsm.backend.Utils;

import com.fsm.backend.Enums.TYPE;
import com.sun.net.httpserver.HttpHandler;

import java.util.Map;

public class Request {

    private String action;
    private TYPE type;
    private Map<String, String> params;
    private Class<? extends HttpHandler> controller;

    public Request(String action,
                   String type,
                   Map<String, String> params,
                   Class<? extends HttpHandler> controller) {
        this.action = action;
        this.type = TYPE.valueOf(type);
        this.params = params;
        this.controller = controller;
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

    public Class<? extends HttpHandler> getController() {
        return controller;
    }

    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", type=" + type +
                ", params=" + params +
                ", controller=" + controller.getSimpleName() +
                '}';
    }
}
