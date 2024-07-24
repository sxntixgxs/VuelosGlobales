package com.vuelosglobales.plane.application.services;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.domain.ports.in.PlaneOperations;
import com.vuelosglobales.plane.domain.ports.out.PlaneRepository;

public class PlaneServiceImp implements PlaneOperations {
    private final PlaneRepository planeRepository;

    public PlaneServiceImp(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Plane addPlane(Plane plane) {
        return planeRepository.savePlane(plane);
    }
    @Override
    public Optional<Plane> findPlaneById(String id) {
        return planeRepository.findPlaneById(id);
    }

    @Override
    public void deletePlaneById(String id) {
        planeRepository.deletePlanebyId(id);
    }

    @Override
    public Plane updatePlane(Plane plane) {
        Optional<Plane> existingPlane = planeRepository.findPlaneById(plane.getId());
        if(existingPlane.isPresent()){
            return planeRepository.updatePlane(plane);
        }else{
            throw new IllegalArgumentException("Plane with ID "+plane.getId()+"does not exist.");
        }
    }

    @Override
    public void getAllPlanes() {
        List<List<String>> wrap = planeRepository.getAllPlanes();
        for (List<String> plane : wrap) {
            plane.forEach(System.out::println);
            System.out.println(" ");
        }
    }




    
}
