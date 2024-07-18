package com.vuelosglobales.plane.domain.ports.out;

import java.util.List;

public interface SpecificRepository {
    List<String> getStatus();
    List<String> getAirline();
    List<String> getModel();
}
