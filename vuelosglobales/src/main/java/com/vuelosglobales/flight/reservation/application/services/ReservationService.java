package com.vuelosglobales.flight.reservation.application.services;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.domain.ports.in.ReservationOperations;
import com.vuelosglobales.flight.reservation.infrastructure.repositories.ReservationRepositoryImp;

public class ReservationService implements ReservationOperations{
    private final ReservationRepositoryImp reservationRepositoryImp;

    public ReservationService(ReservationRepositoryImp reservationRepositoryImp) {
        this.reservationRepositoryImp = reservationRepositoryImp;
    }

    @Override
    public Optional<Reservation> createReservation(Reservation reservation) {
        return reservationRepositoryImp.addReservation(reservation);
        
    }

    @Override
    public List<List<String>> findReservationByCustomerId(String idCustomer) {
        return reservationRepositoryImp.findReservationByCustomerId(idCustomer);
    }

    @Override
    public List<List<String>> findReservationByTripId(int idTrip) {
        return reservationRepositoryImp.findReservationByTripId(idTrip);
    }

    @Override
    public boolean deleteReservation(int idReservation) {
        return reservationRepositoryImp.deleteReservation(idReservation);
    }

    @Override
    public List<String> findReservationById(int idReservation) {
        return reservationRepositoryImp.getReservationById(idReservation);
    }

    @Override
    public Optional<Reservation> findReservationByComponents(Reservation reservation) {
        return reservationRepositoryImp.getReservationByComponents(reservation);
    }




}
