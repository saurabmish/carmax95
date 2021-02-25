package com.carmaxx.rental.dao;

import com.carmaxx.rental.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarDAO {

    int insertCar(UUID id, Car car);

    default int insertCar(Car car) {
        UUID id = UUID.randomUUID();
        return insertCar(id, car);
    }

    List<Car> selectAllCars();
}