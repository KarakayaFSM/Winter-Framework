package com.fsm;

import com.fsm.Annotation.Controller;
import com.fsm.Utlis.PathScanner;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Optional;

public class Main {
    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        List<Class<?>> controllers = m.getControllers();
        m.createContexts(controllers);
        server.setExecutor(null);
        server.start();
    }

    private List<Class<?>> getControllers() {
        return PathScanner.getInstance()
                .getClassesOf("com.fsm.Controller",
                        cls -> cls.isAnnotationPresent(Controller.class));
    }

    void createContexts(List<Class<?>> controllers) {
        controllers
                .forEach(cls ->
                        server.createContext('/' + cls.getAnnotation(Controller.class).path(),
                                (HttpHandler) getInstance(cls)));
    }


    private Object getInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = Optional.of(cls.
                    getDeclaredConstructor(null)
                    .newInstance(null))
                    .orElseThrow(() -> new RuntimeException("instance could not be created"));
        } catch (IllegalAccessException |
                InvocationTargetException |
                InstantiationException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
