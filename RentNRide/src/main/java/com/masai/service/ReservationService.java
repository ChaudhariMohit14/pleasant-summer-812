package com.masai.service;

import java.util.List;

import com.masai.dao.ReservationDAO;
import com.masai.model.Reservation;

public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }

    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationDAO.getReservationById(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        reservationDAO.deleteReservation(reservation);
    }
}
