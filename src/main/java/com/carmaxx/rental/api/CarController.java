package com.carmaxx.rental.api;

import java.util.List;
import java.util.UUID;

import com.carmaxx.rental.model.Car;
import com.carmaxx.rental.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/car")
@RestController
public class CarController {
    
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public void addCar(@RequestBody Car car) {
        carService.addCar(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path = "{id}")
    public Car getCar(@PathVariable("id") UUID id) {
        return carService.getCar(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCar(@PathVariable("id") UUID id) {
        carService.deleteCar(id);
    }

    @PutMapping(path = "{id}")
    public void updateCar(@PathVariable("id") UUID id, @RequestBody Car carToUpdate) {
        carService.updateCar(id, carToUpdate);
    }
}
