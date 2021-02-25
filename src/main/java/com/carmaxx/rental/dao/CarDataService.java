package com.carmaxx.rental.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.carmaxx.rental.model.Car;

public class CarDataService implements CarDAO {

    private static List<Car> DB = new ArrayList<>();

    @Override
    public int insertCar(UUID id, Car car) {
        DB.add(new Car(id, car.getName()));
        return 1;
    }
}