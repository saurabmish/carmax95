package com.carmaxx.rental.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private final UUID id;
    private final String name;

    public Car(@JsonProperty("id") UUID id, 
               @JsonProperty("name") String name) {
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