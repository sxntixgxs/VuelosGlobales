package com.vuelosglobales.plane.application.services;

import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.domain.ports.out.ShowEnteredDataRepository;

public class ShowEnteredDataService {
    private final ShowEnteredDataRepository showEnteredDataRepository;

    public ShowEnteredDataService(ShowEnteredDataRepository showEnteredDataRepository) {
        this.showEnteredDataRepository = showEnteredDataRepository;
    }
    public void showPlaneEntered(Plane plane){
        showEnteredDataRepository.showPlaneEntered(plane);
    }
}
