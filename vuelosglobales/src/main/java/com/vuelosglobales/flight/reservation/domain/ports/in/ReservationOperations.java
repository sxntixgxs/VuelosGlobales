package com.vuelosglobales.flight.reservation.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.reservation.domain.models.Reservation;

public interface ReservationOperations {
    Optional<Reservation> createReservation(Reservation reservation);
    List<List<String>> findReservationByCustomerId(String idCustomer);
    List<List<String>> findReservationByTripId(int idTrip);
    boolean deleteReservation(int idReservation);
    List<String> findReservationById(int idReservation);
    Optional<Reservation> findReservationByComponents(Reservation reservation);
}
