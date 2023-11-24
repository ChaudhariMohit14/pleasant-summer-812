package com.masai.controller;

import java.util.List;

import com.masai.model.Car;
import com.masai.service.CarService;

public class CarController {

    private CarService carService;

    public CarController() {
        this.carService = new CarService();
    }

    public void addCar(Car car) {
        carService.addCar(car);
    }

    public Car getCarById(Long carId) {
        return carService.getCarById(carId);
    }

    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    public void updateCar(Car car) {
        carService.updateCar(car);
    }

    public void deleteCar(Car car) {
        carService.deleteCar(car);
    }
}
