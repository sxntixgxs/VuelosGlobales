package com.vuelosglobales.plane.application.services;

import java.util.List;

import com.vuelosglobales.plane.domain.ports.out.SpecificRepository;

public class InfoService {
    private final SpecificRepository specificRepository;

    public InfoService(SpecificRepository specificRepository) {
        this.specificRepository = specificRepository;
    }
    public List<String> getAirline(){
        return specificRepository.getAirline();
    }
    public List<String> getStatus(){
        return specificRepository.getStatus();
    }
    public List<String> getModel(){
        return specificRepository.getModel();
    }
}
