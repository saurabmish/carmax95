package com.carmaxx.rental.api;

import com.carmaxx.rental.model.Car;
import com.carmaxx.rental.service.CarService;

public class CarController {
    
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void addCar(Car car) {
        carService.addCar(car);
    }
}
