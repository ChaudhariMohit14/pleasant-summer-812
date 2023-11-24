package com.masai.controller;

import java.util.List;

import com.masai.model.Reservation;
import com.masai.service.ReservationService;

public class ReservationController {

    private ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService();
    }

    public void addReservation(Reservation reservation) {
        reservationService.addReservation(reservation);
    }

    public Reservation getReservationById(Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    public void updateReservation(Reservation reservation) {
        reservationService.updateReservation(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        reservationService.deleteReservation(reservation);
    }
}
