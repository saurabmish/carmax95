package com.carmaxx.rental.service;

import com.carmaxx.rental.dao.CarDAO;
import com.carmaxx.rental.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {
    
    private final CarDAO carDAO;

    @Autowired
    public CarService(@Qualifier("in-memory data") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public int addCar(Car car) {
        return carDAO.insertCar(car);
    }

    public List<Car> getAllCars() {
        return carDAO.selectAllCars();
    }

    public Optional<Car> getCar(UUID id) {
        return carDAO.selectCar(id);
    }

    public int deleteCar(UUID id) {
        return carDAO.deleteCar(id);
    }

    public int updateCar(UUID id, Car newcar) {
        return carDAO.updateCar(id, newcar);
    }
}
