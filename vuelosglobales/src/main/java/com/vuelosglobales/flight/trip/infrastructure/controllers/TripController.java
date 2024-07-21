package com.vuelosglobales.flight.trip.infrastructure.controllers;

import java.util.ArrayList;
import java.util.List;

import com.vuelosglobales.flight.employee.application.services.CrewService;
import com.vuelosglobales.flight.employee.application.services.EmployeeService;
import com.vuelosglobales.flight.employee.infrastructure.controllers.CrewController;
import com.vuelosglobales.flight.trip.application.service.TripService;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.application.services.ShowEnteredDataService;

public class TripController {
    private final CrewService crewService;
    private final TripService tripService;
    private final ShowEnteredDataService showEnteredDataService;
    private final PlaneServiceImp planeService;
    public TripController(CrewService crewService, TripService tripService,ShowEnteredDataService showEnteredDataService,PlaneServiceImp planeServiceImp) {
        this.crewService = crewService;
        this.tripService = tripService;
        this.showEnteredDataService = showEnteredDataService;
        this.planeService = planeServiceImp;
    }
    public void assignCrewToTrip(){
        List<String> tripShowList = new ArrayList<>();
        List<String> receivedTripList = tripService.showTrips();
        tripShowList.add("Trip ID -> "+receivedTripList.get(0));
        tripShowList.add("Depature -> "+receivedTripList.get(1));
        tripShowList.add("Arrival -> "+receivedTripList.get(2));
        tripShowList.add("Date -> "+receivedTripList.get(4));
        tripShowList.add("Status -> "+receivedTripList.get(5));//aqui estoy obteniendo el status del avion, crear tabla tripstatus y que muestre ese
        tripShowList.forEach(System.out::println);

        //obtener crew
        System.out.println("CREW DATA: ");
        crewService.showCrew(Integer.valueOf(receivedTripList.get(3)));

        //obtener Plane
        showEnteredDataService.showPlaneEntered(planeService.findPlaneById(receivedTripList.get(6)).get());
    }
}
