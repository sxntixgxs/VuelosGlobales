package com.vuelosglobales.flight.fare.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.fare.domain.models.Fare;

public interface FareRepository {
    Optional<Fare> addFare(Fare fare);
    Optional<Fare> updateFare(Fare fare);
    Optional<Fare> getFareById(int id);
    List<String> getAllFares();
    boolean deleteFare(int id);
}
