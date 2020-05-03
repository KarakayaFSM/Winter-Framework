package com.fsm.backend.Objects.Valuable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ValuableRepository {

    private static final List<Valuable> valuables = Arrays.asList(
            new Valuable("Antik Vazo", 100),
            new Valuable("Klasik Araba", 200),
            new Valuable("Çini", 300)
    );

    public static List<Valuable> getAllAuctions() {
        return new ArrayList<>(valuables);
    }

    public static Valuable findByID(UUID id) {
        return valuables.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow();
    }

}
