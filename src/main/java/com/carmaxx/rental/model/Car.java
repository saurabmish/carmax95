package com.carmaxx.rental.model;

import java.util.UUID;

public class Car {
    private final UUID id;
    private final String name;

    public Car(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}