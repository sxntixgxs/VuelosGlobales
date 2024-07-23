package com.vuelosglobales.flight.fare.application.service;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.fare.domain.models.Fare;
import com.vuelosglobales.flight.fare.domain.ports.in.FareOperations;
import com.vuelosglobales.flight.fare.domain.ports.out.FareRepository;

public class FareService implements FareOperations{
    private final FareRepository fareRepository;

    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    @Override
    public Optional<Fare> addFare(Fare fare) {
        /*
         * comprobar si ese id ya existe
         *  + si ese es el caso, devolver empty
         * añadir
         */
        Optional<Fare> optionalFare = findFareById(fare.getId());
        if(optionalFare.isPresent()){
            return Optional.empty();
        }else{
            return fareRepository.addFare(fare);
        }
    }

    @Override
    public Optional<Fare> updateFare(Fare fare) {
        /*
         * Comprobar si existe el fare
         *  + si no existe, retornar vacio
         * actualizar
         */
        Optional<Fare> optionalFare = findFareById(fare.getId());
        if(optionalFare.isPresent()){
            return fareRepository.updateFare(fare);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Fare> findFareById(int id) {
        return fareRepository.getFareById(id);
    }

    @Override
    public List<String> showAllFares() {
        return fareRepository.getAllFares();
    }

    @Override
    public boolean deleteFare(int id) {
        Optional<Fare> optionalFare = findFareById(id);
        if(optionalFare.isPresent()){
            return fareRepository.deleteFare(id);
        }else{
            return false;
        }
    }
    
    
}
