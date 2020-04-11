package com.fsm.Models;

import java.util.Arrays;
import java.util.List;

public class PersonRepo {

    private static List<Person> people = Arrays.asList(
            new Person(1, "ahmad", "ak", 20),
            new Person(2, "mehmet", "bak", 21),
            new Person(3, "hasan", "şaş", 24)
    );

    public static Person findById(int id) {
        return people.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No Such Person with id: " + id + "found"));
    }

    public static Person findByIdAndName(int id, String name) {
        return people.stream()
                .filter(p -> p.getId() == id &&
                             p.getFirstName().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "No Such Person with id: " + id + "found"));
    }

}