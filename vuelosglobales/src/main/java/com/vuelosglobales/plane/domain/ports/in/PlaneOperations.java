package com.vuelosglobales.plane.domain.ports.in;

import com.vuelosglobales.plane.domain.models.Plane;

public interface PlaneOperations {
    Plane addPlane(Plane plane);
    Plane updatePlane(Plane plane);
    void deletePlane(String id);
    Plane checkPlane(String id);
}
