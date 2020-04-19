package com.fsm.backend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.backend.Annotation.Action;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ControllerUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Request getRequest(HttpExchange httpExchange) {
        String requestMethod = httpExchange.getRequestMethod();
        Map<String, String> params = getRequestParams(httpExchange);
        String action = getAction(httpExchange);
        Class<? extends HttpHandler> controller =
                httpExchange.getHttpContext().getHandler().getClass();
        return new Request(action, requestMethod, params, controller);
    }

    static String getAction(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getPath();
        //substring to remove space at the beginning of parts
        String[] parts = path.substring(1).split("/");
        return parts.length > 1 ? parts[1] : "index";
    }

    public static String responseAsJsonString(Request request) {
        Method method = getMethod(request);
        return getResponse(method, request);
    }

    private static String getResponse(Method method, Request request) {
        String result = "error in getResult";
        try {
            Object res = getInvokeResult(method, request);
            result = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(result);
    }

    private static Object getInvokeResult(Method method, Request request) {
        Object result = null;
        try {
            result = method.invoke(getInstanceOf(request.getController()),
                    getArgs(request));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(result);
    }

    private static Method getMethod(Request request) {
        Method result = null;
        for (Method method : request.getController().getMethods()) {
            Action action = method.getAnnotation(Action.class);
            if (pathAndTypeMatches(request, action)) {
                result = method;
                break;
            }
        }
        return Objects.requireNonNull(result);
    }

    private static boolean pathAndTypeMatches(Request request, Action action) {
        return action.path().equals(request.getAction()) &&
                action.type().equals(request.getType());
    }

    private static Object[] getArgs(Request request) {
        Object[] args = request.getParams().values().toArray();
        List.of(args).forEach(System.out::println);
        return args.length == 1 ? null : args;
    }

    private static Object getInstanceOf(Class<?> controller) {
        return MainUtils.getInstance(controller);
    }

    private static Map<String, String> getRequestParams(HttpExchange httpExchange) {
        String query = httpExchange.getRequestURI().getQuery();
        return queryToMap(query);
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        query = query == null ? "" : query;
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
