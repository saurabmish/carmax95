package com.carmaxx.rental.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.carmaxx.rental.model.Car;

import org.springframework.stereotype.Repository;

@Repository("in-memory data")
public class CarDataService implements CarDAO {

    private static List<Car> DB = new ArrayList<>();

    @Override
    public int insertCar(UUID id, Car car) {
        DB.add(new Car(id, car.getName()));
        return 1;
    }

    @Override
    public List<Car> selectAllCars() {
        return DB;
    }
}