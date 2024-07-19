package com.vuelosglobales.plane.domain.ports.out;

import com.vuelosglobales.plane.domain.models.Plane;

public interface ShowEnteredDataRepository {
    void showPlaneEntered(Plane plane);
}
