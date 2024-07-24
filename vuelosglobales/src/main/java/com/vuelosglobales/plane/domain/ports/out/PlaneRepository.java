package com.vuelosglobales.plane.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;

public interface PlaneRepository {
    Plane savePlane(Plane plane);
    Optional<Plane> findPlaneById(String id);
    void deletePlanebyId(String id);
    Plane updatePlane(Plane plane);
    List<List<String>> getAllPlanes();
}
