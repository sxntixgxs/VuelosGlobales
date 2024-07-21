package com.vuelosglobales.flight.trip.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.trip.domain.models.Trip;

public interface TripOperations {
    Optional<Trip> findTripById(int id);
    Optional<Trip> updateTrip(Trip trip);
    boolean deleteTrip(int id);
    List<String> showTrips();
    
}