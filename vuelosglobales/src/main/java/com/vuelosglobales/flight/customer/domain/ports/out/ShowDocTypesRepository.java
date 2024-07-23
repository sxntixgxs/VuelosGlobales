package com.vuelosglobales.flight.customer.domain.ports.out;

import java.util.List;

public interface ShowDocTypesRepository {
    List<String> getAllDocTypes();
}
