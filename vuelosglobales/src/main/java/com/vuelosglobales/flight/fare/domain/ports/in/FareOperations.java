package com.vuelosglobales.flight.fare.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.fare.domain.models.Fare;

public interface FareOperations {
    /*
     * Consultar tarifa de vuelo find,showAll y dentro de la lista buscar la tarfa?
     * Eliminar tarifa de vuelo find,delete
     * Actualizar tarifa de vuelo find,update
     * Registrar tarifa de vuelo add
     */
    Optional<Fare> addFare(Fare fare);
    Optional<Fare> updateFare(Fare fare);
    Optional<Fare> findFareById(int id);
    List<String> showAllFares();
    boolean deleteFare(int id);
}
