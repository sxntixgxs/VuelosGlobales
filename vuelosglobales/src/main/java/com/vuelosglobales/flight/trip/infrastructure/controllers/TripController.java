package com.vuelosglobales.flight.trip.infrastructure.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.employee.application.services.CrewService;
import com.vuelosglobales.flight.employee.application.services.EmployeeService;
import com.vuelosglobales.flight.employee.domain.ports.in.DateValidator;
import com.vuelosglobales.flight.employee.infrastructure.controllers.CrewController;
import com.vuelosglobales.flight.trip.application.service.DateValidatorService;
import com.vuelosglobales.flight.trip.application.service.TripService;
import com.vuelosglobales.flight.trip.domain.models.Trip;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.application.services.ShowEnteredDataService;

public class TripController {
    private final CrewService crewService;
    private final TripService tripService;
    private final ShowEnteredDataService showEnteredDataService;
    private final PlaneServiceImp planeService;
    private final CrewController crewController;
    private final DateValidatorService dateValidator;
    public TripController(CrewService crewService, TripService tripService,ShowEnteredDataService showEnteredDataService,PlaneServiceImp planeServiceImp,CrewController crewController,DateValidatorService dateValidator) {
        this.crewService = crewService;
        this.tripService = tripService;
        this.showEnteredDataService = showEnteredDataService;
        this.planeService = planeServiceImp;
        this.crewController = crewController;
        this.dateValidator = dateValidator;
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
        boolean continueLoop = true;
        while(continueLoop){

            System.out.println("1. Show crew info ...");
            System.out.println("2. Show plane info ...");
            System.out.println("10. Exit ...");
            try{
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        int idCrew = Integer.valueOf(selectedTrip.get(3));
                        //if(idCrew==0){
                            System.out.println("Assign crew: ");
                            Optional<Integer> optionalCreated = crewController.createCrew();
                            if(optionalCreated.isPresent()){
                                Optional<Trip> updatedTrip = tripService.findTripById(tripId);
                                if(updatedTrip.isPresent()){
                                    updatedTrip.get().setIdCrew(optionalCreated.get());
                                    Optional<Trip> optionalTrip = tripService.updateCrewTrip(updatedTrip.get());
                                    if(optionalTrip.isPresent()){
                                        System.out.println("CREW: "+updatedTrip.get().getIdCrew()+"ASSIGNED ON TRIP: "+tripId);
                                        break;
                                    }else{
                                        System.out.println("no se pudo actualizar el trip");
                                    }
                                }
                            }

                        //}
                        break;
                    case 2:
                        showEnteredDataService.showPlaneEntered(planeService.findPlaneById(selectedTrip.get(6)).get());
                        sc.nextLine();
                        break;
                    case 10:
                        continueLoop = false; //rompe el bucle sin cerrar el programa 
                        break;
                    default:
                    System.out.println("Invalid option");
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
    public int selectTrip(){
        /*
         * metodo para seleccionar viaje
         * muestra todos los viajes y se devuelve la seleccion
         */
        Scanner sc = new Scanner(System.in);
        int idTripSelected=0;
        while(true){
             System.out.println("TRIPS AVALIABLES");
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
                     return tripId;
                 }
             }
             if(selectedTrip == null){
                 System.out.println("Invalid input, try again");
                 continue;
             }
        }
    }
    public void checkTrip(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter the trip id: ");
            int tripId = sc.nextInt();
            if(tripService.findTripById(tripId).isPresent()){
                tripService.showTrips().get(tripId-1).forEach(System.out::println);
                sc.nextLine();
            }else{
                System.out.println("Trip not found");
                sc.nextLine();
            }
        
    }
    public void assignPlaneToTrip(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("These are the available trips");
            List<List<String>> tripListWrap = tripService.showTrips();
            for (List<String> tripList : tripListWrap) {
                System.out.println(" ");
                tripList.forEach(System.out::println);
            }
            try{
                System.out.println("Enter the trip id: ");
                int tripIdSelected = sc.nextInt();
                sc.nextLine();
                if(tripService.findTripById(tripIdSelected).isPresent()){
                    Trip updatedTrip = tripService.findTripById(tripIdSelected).get();
                    planeService.getAllPlanes();
                    String idNewPlane = sc.nextLine();
                    if(planeService.findPlaneById(idNewPlane).isPresent()){
                        updatedTrip.setIdAirplane(idNewPlane);
                        tripService.updateTrip(updatedTrip);
                        System.out.println();
                        System.out.println("Plane "+idNewPlane+" assigned to the flight "+tripIdSelected);
                        sc.nextLine();
                        break;
                    }else{
                        System.out.println("Invalid input, try again");
                        sc.nextLine();
                        continue;
                    }
                }else{
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }

        }

    }
    public void updateTrip(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter the trip id: ");
            try{
                int tripId = sc.nextInt();
                if(tripService.findTripById(tripId).isPresent()){
                    tripService.showTrips().get(tripId-1);
                    createTripWithoutCrew(tripId);
                    break;
                }else{
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
    }
    public void createTripWithoutCrew(int tripId){
        Scanner sc = new Scanner(System.in);
        int idRoute = 0;
        String date = "";
        int idStatus = 0;
        String idPlane = "";
        while(true){
            sc.nextLine();
            tripService.showRouteWithId();
            System.out.println("Enter the id of the route: ");
            try{
                idRoute = sc.nextInt();
                if(!tripService.findRouteById(idRoute).isPresent()){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    sc.nextLine();
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
         }
         while(true){
            System.out.println("Enter the trip date: FORMAT YYYY-MM-DD");
            date = sc.nextLine();
            boolean check = dateValidator.isValid(date);
            if (check) {
                sc.nextLine();
                break;
            } else {
                System.out.println("Use the correct DATE FORMAT ");
                continue;
            }
        }
        while(true){
            tripService.showStatusWithId();
            System.out.println("Enter the status ID: ");
            try {
                idStatus = sc.nextInt();
                if(idStatus<1 || idStatus>10){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    sc.nextLine();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again! ");
            } 
        }
        while(true){
            planeService.getAllPlanes();
            System.out.println("Enter the plane ID: ");
            try{
                idPlane = sc.nextLine();
                if(!planeService.findPlaneById(idPlane).isPresent()){
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }else{
                    sc.nextLine();
                    break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input, try again! ");
            } 
        }
        Trip trip = new Trip(tripId, idRoute, date, idStatus, idPlane);
        if(tripService.updateTrip(trip).isPresent()){
            System.out.println("TRIP "+tripId+" updated successfuly");
        }
    }
}
