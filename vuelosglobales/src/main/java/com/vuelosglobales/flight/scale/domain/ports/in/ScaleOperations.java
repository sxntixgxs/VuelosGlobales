package com.vuelosglobales.flight.scale.domain.ports.in;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.scale.domain.models.Scale;

public interface ScaleOperations {
    List<List<String>> findScalesOfTrip(int idTrip);
    List<List<String>> findScaleByComponents(int idScaleCity, int idTrip, Date date);
    void showScales(List<List<String>> scaleListWrapper);
    Optional<Scale> updateScale(Scale scale);
    boolean deleteScale(int idScale);
    List<String> getCityListId();
    List<Integer> getTripIdList();
    List<Integer> getScalesInt();
}
