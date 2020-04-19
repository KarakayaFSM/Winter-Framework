package com.fsm.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.Annotation.Controller;
import com.fsm.Annotation.Action;
import com.fsm.Enums.TYPE;
import com.fsm.Utlis.Request;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Controller(path = "hello")
public class HomeController implements HttpHandler {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Request request = getRequest(httpExchange);
        String response = getResponseAsJson(request);
        httpExchange.sendResponseHeaders(200, 0);
        OutputStream body = httpExchange.getResponseBody();
        body.write(response.getBytes());
        body.close();
        //TODO getRequestBody outputStream kapatmazsak ? ne olur
    }

    private Request getRequest(HttpExchange httpExchange) {
        String requestMethod = httpExchange.getRequestMethod();
        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> params = queryToMap(query);
        String path = httpExchange.getRequestURI().getPath();
        String action = getAction(path);
        return new Request(action, requestMethod, params);
    }


    @Action(type = TYPE.GET)
    private String Hello() {
        return "Hello";
    }

    String getResponseAsJson(Request request) {
        if (request.getAction().equals("index")) {
            return Hello();
        } else {
            return getAsJsonString(request);
        }
    }

    private String getAsJsonString(Request request) {
        try {
            Method method = getMethod(request);
            Object result = getResult(method, request.getParams());
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "cannot get value";
    }

    Method getMethod(Request request) {
        for (Method method : this.getClass().getMethods()) {
            Action action = method.getAnnotation(Action.class);
            if (action.path().equals(request.getAction()) &&
                    action.type().equals(request.getType())) {
                return method;
            }
        }
        return null;
    }

    private Object getResult(Method method, Map<String, String> params) {
        try {
            return method.invoke(this, params.values().toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    String getAction(String path) {
        //substring to remove space at the beginning of parts
        String[] parts = path.substring(1).split("/");
        return parts.length > 1 ? parts[1] : "index";
    }

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

}
