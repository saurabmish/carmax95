package com.carmaxx.rental.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Car {

    private final UUID id;
    private final Character available;
    private final String name;
    private final String manufacturer;
    private final Integer numPreviousOwners;

    public Car(@JsonProperty("id") UUID id,
               @JsonProperty("available") Character available,
               @JsonProperty("name") String name,
               @JsonProperty("manufacturer") String manufacturer,
               @JsonProperty("owned times") Integer numPreviousOwners) {
        this.id = id;
        this.available = available;
        this.name = name;
        this.manufacturer = manufacturer;
        this.numPreviousOwners = numPreviousOwners;
    }

    public UUID getId() {
        return id;
    }

    public Character getAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getNumPreviousOwners() {
        return numPreviousOwners;
    }
}