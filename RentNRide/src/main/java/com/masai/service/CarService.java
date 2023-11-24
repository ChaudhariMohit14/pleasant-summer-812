package com.masai.service;

import java.util.List;

import com.masai.dao.CarDAO;
import com.masai.model.Car;

public class CarService {

    private CarDAO carDAO;

    public CarService() {
        this.carDAO = new CarDAO();
    }

    public void addCar(Car car) {
        carDAO.addCar(car);
    }

    public Car getCarById(Long carId) {
        return carDAO.getCarById(carId);
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }
}
