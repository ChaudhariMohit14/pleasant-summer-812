package com.masai.UI;

import java.util.List;

import com.masai.Entity.Car;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AdministratorService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public AdministratorService() {
        emf = Persistence.createEntityManagerFactory("project");
        em = emf.createEntityManager();
    }

    public void addCar(Car car) {
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    public void updateCar(Car car) {
        em.getTransaction().begin();
        em.merge(car);
        em.getTransaction().commit();
    }

    public void deleteCar(Long carId) {
        em.getTransaction().begin();
        Car car = em.find(Car.class, carId);
        if (car != null) {
            car.setDeleted(true);
            em.merge(car);
        }
        em.getTransaction().commit();
    }

    public List<Car> getAllCars() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public Car getCarById(Long carId) {
        return em.find(Car.class, carId);
    }

    public void close() {
        em.close();
        emf.close();
    }
}
