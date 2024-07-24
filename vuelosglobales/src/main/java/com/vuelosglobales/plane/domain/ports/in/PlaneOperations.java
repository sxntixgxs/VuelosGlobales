package com.vuelosglobales.plane.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;

public interface PlaneOperations {
    Plane addPlane(Plane plane);
    Plane updatePlane(Plane plane);
    void deletePlaneById(String id);
    Optional<Plane> findPlaneById(String id);
    void getAllPlanes();
}
