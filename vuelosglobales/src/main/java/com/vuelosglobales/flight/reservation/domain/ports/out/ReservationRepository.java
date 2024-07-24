package com.vuelosglobales.flight.reservation.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.reservation.domain.models.Reservation;

public interface ReservationRepository {
    Optional<Reservation> addReservation(Reservation reservation);
    List<List<String>> findReservationByCustomerId(String idCustomer);
    List<List<String>> findReservationByTripId(int idTrip);
    boolean deleteReservation(int idReservation);
    List<String> getReservationById(int idReservation);
    Optional<Reservation> getReservationByComponents(Reservation reservation);
}
