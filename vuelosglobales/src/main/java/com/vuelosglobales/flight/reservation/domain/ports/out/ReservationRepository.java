package com.vuelosglobales.flight.reservation.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.reservation.domain.models.Reservation;

public interface ReservationRepository {
    Optional<Reservation> addReservation(Reservation reservation);
    List<Reservation> findReservationByCustomerId(String idCustomer);
    List<Reservation> findReservationByTripId(String idTrip);
    boolean deleteReservation(int idReservation);
    List<String> getReservationById(int idReservation);
    Optional<Reservation> getReservationByComponents(Reservation reservation);
}
