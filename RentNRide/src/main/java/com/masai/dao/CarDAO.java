package com.masai.dao;

import java.util.List;

import com.masai.model.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CarDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addCar(Car car) {
        entityManager.persist(car);
    }

    public Car getCarById(Long carId) {
        return entityManager.find(Car.class, carId);
    }

    public List<Car> getAllCars() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public void updateCar(Car car) {
        entityManager.merge(car);
    }

    public void deleteCar(Car car) {
        entityManager.remove(car);
    }
}
