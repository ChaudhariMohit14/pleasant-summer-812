package com.masai.dao;

import java.util.List;

import com.masai.model.Reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ReservationDAO {
	
	@PersistenceContext
    private EntityManager entityManager;

    public void addReservation(Reservation reservation) {
        entityManager.persist(reservation);
    }

    public Reservation getReservationById(Long reservationId) {
        return entityManager.find(Reservation.class, reservationId);
    }

    public List<Reservation> getAllReservations() {
        return entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class).getResultList();
    }

    public void updateReservation(Reservation reservation) {
        entityManager.merge(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        entityManager.remove(reservation);
    }

}
