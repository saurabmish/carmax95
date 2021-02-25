package com.carmaxx.rental.service;

import com.carmaxx.rental.dao.CarDAO;
import com.carmaxx.rental.model.Car;

public class CarService {
    
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public int addCar(Car car) {
        return carDAO.insertCar(car);
    }
}
