package com.vuelosglobales.flight.scale.application.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.scale.domain.models.Scale;
import com.vuelosglobales.flight.scale.domain.ports.in.ScaleOperations;
import com.vuelosglobales.flight.scale.domain.ports.out.ScaleRepository;

public class ScaleService implements ScaleOperations{
    private final ScaleRepository scaleRepository;

    public ScaleService(ScaleRepository scaleRepository) {
        this.scaleRepository = scaleRepository;
    }

    @Override
    public List<List<String>> findScalesOfTrip(int idTrip) {
        return scaleRepository.getScalesOfTrip(idTrip);
    }

    @Override
    public List<List<String>> findScaleByComponents(int idScaleCity, int idTrip, Date date) {
        return scaleRepository.getScalesByComponents(idScaleCity, idTrip, date);
    }

    @Override
    public void showScales(List<List<String>> scaleListWrapper) {
        for (List<String> scaleData : scaleListWrapper) {
            scaleData.forEach(System.out::println);
            System.out.println(" ");
        }
        
    }

    @Override
    public Optional<Scale> updateScale(Scale scale) {
        if (scale.getId() <= 0 || scale.getIdScaleCity() <= 0 || scale.getIdTrip() <= 0 || scale.getDate() == null) {
            throw new IllegalArgumentException("Invalid scale parameters.");
        }
        return scaleRepository.updateScale(scale);
    }
    @Override
    public boolean deleteScale(int idScale) {
        boolean isDeleted = scaleRepository.deleteScale(idScale);
      if (isDeleted) {
        System.out.println("Deleted successfuly");
        return true;
      }
      return false;
    }

    @Override
    public List<String> getCityListId() {
        List<String> cities = scaleRepository.getCityListId();
        cities.forEach(System.out::println);
        return cities;
    }

    @Override
    public List<Integer> getTripIdList() {
        List<List<String>> tripInfo = scaleRepository.getTripIdList();
        List<Integer> tripIds = new ArrayList<>();
        
        for (List<String> tripDetails : tripInfo) {
            for (String detail : tripDetails) {
                System.out.println(detail);
                if (detail.startsWith("Trip id: ")) {
                    Integer tripId = Integer.parseInt(detail.substring("Trip id: ".length()).trim());
                    tripIds.add(tripId);
                    break; 
                }
            }
        }

        return tripIds;
    }

    @Override
    public List<Integer> getScalesInt() {
        return scaleRepository.getScales();
    }
    
}
