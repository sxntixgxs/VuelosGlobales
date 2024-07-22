package com.vuelosglobales.flight.trip.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.trip.domain.models.Trip;

public interface TripRepository {
    Optional<Trip> findTripById(int id);
    Optional<Trip> updateTrip(Trip trip);
    boolean deleteTrip(int id);
    List<List<String>> ShowTrips();
}
