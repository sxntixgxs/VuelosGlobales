package com.vuelosglobales.flight.trip.infrastructure.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
    private final CrewController crewController;
    public TripController(CrewService crewService, TripService tripService,ShowEnteredDataService showEnteredDataService,PlaneServiceImp planeServiceImp,CrewController crewController) {
        this.crewService = crewService;
        this.tripService = tripService;
        this.showEnteredDataService = showEnteredDataService;
        this.planeService = planeServiceImp;
        this.crewController = crewController;
    }
    public void assignCrewToTrip(){
        Scanner sc = new Scanner(System.in);
        List<List<String>> receivedTripList = tripService.showTrips();
        for(List<String> trip : receivedTripList){
            System.out.println("Trip ID: "+trip.get(0));
            System.out.println("Depature: "+trip.get(1));
            System.out.println("Arrival: "+trip.get(2));
            System.out.println("Date: "+trip.get(4));
            System.out.println("Status: "+trip.get(5));
            System.out.println();
        }
        //solicitar al usuario el viaje
        System.out.println("Select the trip id: ");
        int tripId = sc.nextInt();
        List<String> selectedTrip = null;
        for(List<String> trip : receivedTripList){
            int receivedTripId = Integer.valueOf(trip.get(0));
            if(receivedTripId == tripId){
                selectedTrip = trip;
                break;
            }
        }
        if(selectedTrip == null){
            System.out.println("Invalid input, try again");
            return;
        }
        // receivedTripList.forEach(System.out::println);
        // sc.nextLine();
        while(true){

            System.out.println("1. Show crew info ...");
            System.out.println("2. Show plane info ...");
            System.out.println("10. Exit ...");
            try{
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        int idCrew = Integer.valueOf(selectedTrip.get(3));
                        if(idCrew==0){
                            System.out.println("Assign crew: ");
                            crewController.createCrew();

                        }else{
                            System.out.println("CREW DATA: ");
                            crewService.showCrew(idCrew);
                        }
                        break;
                    case 2:
                        showEnteredDataService.showPlaneEntered(planeService.findPlaneById(selectedTrip.get(6)).get());
                        break;
                    case 10:
                        break;
                    default:
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        //obtener crew

        // crewService.showCrew(receivedTripList.get(3));
        // // System.out.println(Integer.valueOf(receivedTripList.get(3)));
        // crewService.showCrew(Integer.valueOf(receivedTripList.get(3)));
        // sc.nextLine();
        //obtener Plane

    }
}
