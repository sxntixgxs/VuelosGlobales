package com.vuelosglobales.flight.trip.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.trip.domain.models.Trip;

public interface TripOperations {
    Optional<Trip> findTripById(int id);
    Optional<Trip> updateCrewTrip(Trip trip);
    boolean deleteTrip(int id);
    List<List<String>> showTrips();
    Optional<Trip> updateTrip(Trip trip);
    void showCityWithId();
    void showRouteWithId();
    void showStatusWithId();
    Optional<String> findStatusWithId(int id);
    Optional<String> findCityById(int id);
    Optional<String> findRouteById(int id);
}
