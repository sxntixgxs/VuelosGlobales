package com.vuelosglobales.plane.domain.ports.out;

import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;

public interface PlaneRepository {
    Plane savePlane(Plane plane);
    Optional<Plane> checkPlane(String id);
    void deletePlane(String id);
    Plane updatePlane(Plane plane);

}
