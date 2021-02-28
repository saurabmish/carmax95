package com.carmaxx.rental.dao;

import com.carmaxx.rental.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("in-memory data")
public class CarDataService implements CarDAO {

    private static List<Car> DB = new ArrayList<>();

    @Override
    public int insertCar(UUID id, Car car) {
        DB.add(new Car(id, car.getAvailable(), car.getName(), car.getManufacturer(), car.getNumPreviousOwners()));
        return 1;
    }

    @Override
    public List<Car> selectAllCars() {
        return DB;
    }

    @Override
    public Optional<Car> selectCar(UUID id) {
        return DB.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteCar(UUID id) {
        Optional<Car> carMaybe = selectCar(id);
        if (!carMaybe.isEmpty()) {
            DB.remove(carMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateCar(UUID id, Car newCar) {
        return selectCar(id)
                .map(car -> {
                    int carIndex = DB.indexOf(car);
                    if (carIndex >= 0) {
                        //DB.set(carIndex, new Car(id, newCar.getName()));
                        DB.set(carIndex, new Car(id, newCar.getAvailable(), newCar.getName(), newCar.getManufacturer(), newCar.getNumPreviousOwners()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0); 
    }
}