package com.vuelosglobales.flight.trip.application.service;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.trip.domain.models.Trip;
import com.vuelosglobales.flight.trip.domain.ports.in.TripOperations;
import com.vuelosglobales.flight.trip.domain.ports.out.TripRepository;

public class TripService implements TripOperations {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Optional<Trip> findTripById(int id) {
        return tripRepository.findTripById(id);
    }

    @Override
    public Optional<Trip> updateCrewTrip(Trip trip) {
        return tripRepository.updateCrewTrip(trip);
    }

    @Override
    public boolean deleteTrip(int id) {
        return tripRepository.deleteTrip(id);
    }

    @Override
    public List<List<String>> showTrips() {
        return tripRepository.ShowTrips();
    }

    @Override
    public Optional<Trip> updateTrip(Trip trip) {
        return tripRepository.updateTrip(trip);
    }
    
}
