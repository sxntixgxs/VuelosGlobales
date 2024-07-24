package com.vuelosglobales.flight.scale.domain.ports.out;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.scale.domain.models.Scale;

public interface ScaleRepository {
    List<List<String>> getScalesOfTrip(int idTrip);
    List<List<String>> getScalesByComponents(int idScaleCity, int idTrip, Date date);
    Optional<Scale> updateScale(Scale scale);
    boolean deleteScale(int idScale);
    List<String> getCityListId();
    List<List<String>> getTripIdList();
    List<Integer> getScales();
}
