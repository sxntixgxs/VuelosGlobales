package com.vuelosglobales.plane.infrastructure.controllers;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.plane.application.services.DateValidatorImp;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.domain.models.Plane;

public class PlaneController {
    private final PlaneServiceImp planeServiceImp;
    private final DateValidatorImp dateValidatorImp;

    public PlaneController(PlaneServiceImp planeServiceImp,DateValidatorImp dateValidatorImp) {
        this.planeServiceImp = planeServiceImp;
        this.dateValidatorImp = dateValidatorImp;
    }
    public void createPlane(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plane ID: ");
        String id = sc.nextLine();
        System.out.println("Enter plane capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the fabrication date: THIS FORMAT YYYY-MM-DD");
        String date = sc.nextLine();
        if(!dateValidatorImp.isValid(date)){
            System.out.println("Use the correct format: YYYY-MM-DD");
            return;
        }
        System.out.println("Enter the status ID: ");
        int idStatus = sc.nextInt();
        System.out.println("Enter the airline ID: ");
        int idAirline = sc.nextInt();
        System.out.println("Enter the model ID: ");
        int idModel = sc.nextInt();
        Plane plane = new Plane(id, capacity, date, idStatus, idAirline, idModel);
        planeServiceImp.addPlane(plane);
    }
    public Plane findPlaneById(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plane ID: ");
        String id = sc.nextLine();
        Optional<Plane> plane = planeServiceImp.findPlaneById(id);
        return plane.orElse(null);
    }
    public void updatePlane(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plane ID: ");
        String id = sc.nextLine();
        Optional<Plane> plane = planeServiceImp.findPlaneById(id);
        if(!plane.isPresent()){
            System.out.println("Plane not found! ");
        }
        System.out.println("Enter plane capacity: ");
        int capacity = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the fabrication date: THIS FORMAT YYYY-MM-DD");
        String date = sc.nextLine();
        if(!dateValidatorImp.isValid(date)){
            System.out.println("Use the correct format: YYYY-MM-DD");
            return;
        }
        System.out.println("Enter the status ID: ");
        int idStatus = sc.nextInt();
        System.out.println("Enter the airline ID: ");
        int idAirline = sc.nextInt();
        System.out.println("Enter the model ID: ");
        int idModel = sc.nextInt();
        Plane planeUpdate = new Plane(id, capacity, date, idStatus, idAirline, idModel);
        planeServiceImp.updatePlane(planeUpdate);
    }
    public void deletePlaneById(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plane ID: ");
        String id = sc.nextLine();
        Optional<Plane> plane = planeServiceImp.findPlaneById(id);
        if(!plane.isPresent()){
            System.out.println("Plane not found! ");
        }else{
            planeServiceImp.deletePlaneById(plane.get().getId());
        }
    }
}
