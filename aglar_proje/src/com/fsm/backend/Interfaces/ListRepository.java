package com.fsm.backend.Interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ListRepository<T extends MyObject> implements Repository<T> {

    List<T> items = new ArrayList<>();

    @Override
    public T findById(String id) {
        return findById(UUID.fromString(id));
    }

    @Override
    public T findById(UUID id) {
        return items.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<>(items);
    }

    @Override
    public T add(T item) {
        items.add(item);
        return item;
    }

    @Override
    public Collection<T>filter(Predicate<T> condition) {
        return items.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    @Override
    public T update(T updated) {
        removeById(updated.getId());
        return add(updated);
    }

    @Override
    public void removeById(UUID id) {
        System.out.println("id: " + id.toString());
        T toRemove = items.stream()
                .filter(item ->
                        item.getId().equals(id))
                .findFirst().orElseThrow();
        items.remove(toRemove);
    }

}
