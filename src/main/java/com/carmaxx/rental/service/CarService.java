package com.carmaxx.rental.service;

import java.util.List;

import com.carmaxx.rental.dao.CarDAO;
import com.carmaxx.rental.model.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
}
