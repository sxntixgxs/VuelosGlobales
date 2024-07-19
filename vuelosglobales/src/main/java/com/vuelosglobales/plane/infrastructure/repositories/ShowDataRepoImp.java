package com.vuelosglobales.plane.infrastructure.repositories;

import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.domain.ports.out.ShowEnteredDataRepository;
import com.vuelosglobales.plane.domain.ports.out.SpecificRepository;

public class ShowDataRepoImp implements ShowEnteredDataRepository{
    private final SpecificRepository specificRepository;
    public ShowDataRepoImp(SpecificRepository specificRepository) {
        this.specificRepository = specificRepository;
    }

    @Override
    public void showPlaneEntered(Plane plane) {
        System.out.println("This is the plane data entered");
        System.out.println("ID -> "+plane.getId());
        System.out.println("Capacity -> "+plane.getCapacity());
        System.out.println("Fabrication date -> "+plane.getFabrication());
        System.out.println("Status -> "+specificRepository.getStatus().get(plane.getIdStatus()-1));
        System.out.println("Airline -> "+specificRepository.getAirline().get(plane.getIdAirline()-1));
        System.out.println("Model -> "+specificRepository.getModel().get(plane.getIdModel()-1));
    }
}
