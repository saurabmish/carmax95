package com.carmaxx.rental.dao;

import com.carmaxx.rental.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarDAO {

    List<Car> selectAllCars();

    Optional<Car> selectCar(UUID id);

    int deleteCar(UUID id);

    int updateCar(UUID id, Car car);

    int insertCar(UUID id, Car car);
    
    default int insertCar(Car car) {
        UUID id = UUID.randomUUID();
        return insertCar(id, car);
    }    
}