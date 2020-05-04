package com.fsm.backend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Objects.Request.ParamHandler;
import com.fsm.backend.Objects.Request.Request;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ControllerUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Request getRequest(HttpExchange exchange) {
        Map<String, Object> params = ParamHandler.getParams(exchange);
        TYPE type = TYPE.valueOf(exchange.getRequestMethod());
        String action = getAction(exchange);
        Class<? extends HttpHandler> controller = getHandler(exchange);
        String body = ParamHandler.getBody(exchange);
        return new Request()
                .setAction(action)
                .setController(controller)
                .setParams(params)
                .setType(type)
                .setBody(body);
    }

    private static Class<? extends HttpHandler> getHandler(HttpExchange httpExchange) {
        return httpExchange.getHttpContext().getHandler().getClass();
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
        //Object result = new Object();
        try {
            Object[] args = getArgs(request, method);
            result = method.invoke(
                    getInstanceOf(request.getController()),
                    args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(result);
    }

    private static Object[] getArgs(Request request, Method method) {
        if (request.getType().equals(TYPE.GET)) {
            if (request.getParams().isEmpty()) return null;
        }

        if (request.getType().equals(TYPE.POST)) {
            Object object = getTargetObject(request.getBody(), method);
            return new Object[]{object};
        }
        return request.getParams().values().toArray();
    }

    public static Object getTargetObject(String body, Method targetMethod) {

        Class<?> paramType = ParamHandler.getParamType(targetMethod);
        return ParamHandler.getObjectFrom(body, paramType);
    }

    private static String getAction(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getPath();
        //substring to remove space at the beginning of parts
        String[] parts = path.substring(1).split("/");
        return parts.length > 1 ? parts[1] : "index";
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

    private static boolean pathAndTypeMatches(Request request, Action target) {
        return target.path().equals(request.getAction()) &&
                target.type().equals(request.getType());
    }

    private static Object getInstanceOf(Class<?> controller) {
        return MainUtils.getInstance(controller);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
