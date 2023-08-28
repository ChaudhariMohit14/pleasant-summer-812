package com.masai.UI;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.Entity.Car;
import com.masai.Entity.Customer;
import com.masai.Entity.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CustomerService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CustomerService() {
        emf = Persistence.createEntityManagerFactory("project");
        em = emf.createEntityManager();
    }

    public Car getCarById(Long carId) {
        return em.find(Car.class, carId);
    }

    public Reservation createReservation(Customer customer, Car car, LocalDateTime startTime, LocalDateTime endTime) {
        em.getTransaction().begin();
        
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setCar(car);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        
        em.persist(reservation);
        em.getTransaction().commit();
        
        return reservation;
    }

    public void updateReservation(Reservation reservation) {
        em.getTransaction().begin();
        em.merge(reservation);
        em.getTransaction().commit();
    }

    public void cancelReservation(Long reservationId) {
        em.getTransaction().begin();
        Reservation reservation = em.find(Reservation.class, reservationId);
        if (reservation != null) {
            em.remove(reservation);
        }
        em.getTransaction().commit();
    }

    public List<Reservation> getReservationsByCustomer(Customer customer) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.customer = :customer", Reservation.class)
                 .setParameter("customer", customer)
                 .getResultList();
    }

    public Reservation getReservationById(Long reservationId) {
        return em.find(Reservation.class, reservationId);
    }

    public void close() {
        em.close();
        emf.close();
    }
}