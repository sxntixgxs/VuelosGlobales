package com.vuelosglobales.flight.trip.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.trip.domain.models.Trip;

public interface TripRepository {
    Optional<Trip> findTripById(int id);
    Optional<Trip> updateCrewTrip(Trip trip);
    boolean deleteTrip(int id);
    List<List<String>> ShowTrips();
    Optional<Trip> updateTrip(Trip trip);
    List<String> getAllCitiesWithId();
    List<String> getAllRoutesWithId();
    List<String> getAllStatusWithId();
    Optional<String> findCityById(int id);
    Optional<String> findRouteById(int id);
    Optional<String> findStatusById(int id);
}
