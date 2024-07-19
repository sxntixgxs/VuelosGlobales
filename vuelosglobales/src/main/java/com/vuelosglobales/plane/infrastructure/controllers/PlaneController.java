package com.vuelosglobales.plane.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.plane.application.services.DateValidatorImp;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.application.services.ShowEnteredDataService;
import com.vuelosglobales.plane.domain.models.Plane;

public class PlaneController {
    private final PlaneServiceImp planeServiceImp;
    private final DateValidatorImp dateValidatorImp;
    private final InfoController infoController;
    private final ShowEnteredDataService showEnteredDataService;
    // private final SpecificRepositoryImp specificRepositoryImp;

    public PlaneController(PlaneServiceImp planeServiceImp,DateValidatorImp dateValidatorImp,InfoController infoController,ShowEnteredDataService showEnteredDataService) {
        this.planeServiceImp = planeServiceImp;
        this.dateValidatorImp = dateValidatorImp;
        this.infoController = infoController;
        this.showEnteredDataService = showEnteredDataService;
    }
    /*CREATE PLANE SECTION */
    public void createPlane(){
        Scanner sc = new Scanner(System.in);
        String id = "";
        while(true){
            System.out.println("Enter NEW plane ID: ");
            id = sc.nextLine().toUpperCase();
            Optional<Plane> plane = planeServiceImp.findPlaneById(id);
            if(plane.isPresent()){
               System.out.println("Plane with that ID already exists!");
               sc.nextLine();
               continue; 
            }else{
                break;
            }
        }
/*FALTA VERIFICACION DE QUE NO EXISTA ESE ID!!!!!! */
        boolean validInput = false;
        int capacity=0;
        while(!validInput){
            System.out.println("Enter plane capacity: ");
            try{
                capacity = sc.nextInt();
                validInput = true;
            }catch(InputMismatchException e){
                System.out.println("Input invalid, try again ");
                sc.nextLine();
            }
        }
        sc.nextLine();
        String date = "";
        while(true){
            System.out.println("Enter the fabrication date: THIS FORMAT YYYY-MM-DD");
             date = sc.nextLine();        
             if(!dateValidatorImp.isValid(date)){
                System.out.println("Use the correct format: YYYY-MM-DD");
                sc.nextLine();
                continue;
            }else{
                break;
            }
        }
        infoController.showStatuses();
        int idStatus=0;
        while(true){
            System.out.println("Enter the status ID: ");
            try {
                idStatus = sc.nextInt();
                if(idStatus>10){
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
            }
        }

        infoController.showAirlines();
        int idAirline = 0;
        while(true){
            System.out.println("Enter the airline ID: ");
            try {
                idAirline = sc.nextInt();
                if(idAirline>10){
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
            }
        }
        int idModel = 0;
        infoController.showModels();
        while(true){
            System.out.println("Enter the model ID: ");
            try {
                idModel = sc.nextInt();
                if(idStatus>10){
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again!");
                sc.nextLine();
            }
        }
        Plane plane = new Plane(id, capacity, date, idStatus, idAirline, idModel);
       try {
            showEnteredDataService.showPlaneEntered(plane);
            planeServiceImp.addPlane(plane);
       } catch (Exception e) {
            System.out.println("Error creating plane "+e);
       } 
    }
    /*UPDATE PLANE SECTION */
/*     public Plane findPlaneById(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plane ID: ");
        String id = sc.nextLine();
        Optional<Plane> plane = planeServiceImp.findPlaneById(id);
        return plane.orElse(null);
    }
    */
    public void updatePlane(){
        Scanner sc = new Scanner(System.in);
        String id ="";
        while(true){
            infoController.showIds();
            System.out.println("Enter plane ID: ");
            id = sc.nextLine().toUpperCase();
            Optional<Plane> plane = planeServiceImp.findPlaneById(id);
            if(!plane.isPresent()){
                System.out.println("Plane not found");
                sc.nextLine();
                continue;
            }else{
                break;
            }
        }



        int capacity = 0;
        while(true){
            System.out.println("Enter plane capacity: ");
            try {
                capacity = sc.nextInt();
                if(capacity>300 || capacity<5){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
            }
        }



        sc.nextLine();
        String date = "";
        while(true){
            System.out.println("Enter the fabrication date: THIS FORMAT YYYY-MM-DD");

            try{
                date = sc.nextLine();
                if(!dateValidatorImp.isValid(date)){
                    System.out.println("Use the correct format: YYYY-MM-DD");
                    continue;
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Use the correct format: YYYY-MM-DD");
            }

        }





        int idStatus = 0;
        while (true) {
            infoController.showStatuses();
            System.out.println("Enter the status ID: ");
            try {
                idStatus = sc.nextInt();
                if(idStatus<1 || idStatus>10){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again! ");
            }    
        }


        int idAirline = 0;
        while(true){
            infoController.showAirlines();
            System.out.println("Enter the airline ID: ");
            try{
                idAirline = sc.nextInt();
                if(idAirline<1 || idAirline>10){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
            }
        }
        

        int idModel = 0;
        while(true){
            infoController.showModels();
            System.out.println("Enter the model ID ");
            try{
                idModel = sc.nextInt();
                if(idModel<1 || idModel>20){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
            }
        }
        Plane planeUpdate = new Plane(id, capacity, date, idStatus, idAirline, idModel);

        try {
            showEnteredDataService.showPlaneEntered(planeUpdate);
            planeServiceImp.updatePlane(planeUpdate);
       } catch (Exception e) {
            System.out.println("Error creating plane "+e);
       } 
    }
    /*DELETE PLANE SECTIIB */
    public void deletePlaneById(){
        Scanner sc = new Scanner(System.in);
        String id = "";
        while (true) {
            infoController.showIds();
            System.out.println("Enter plane ID: ");
            try{
                id = sc.nextLine().toUpperCase();
                Optional<Plane> plane = planeServiceImp.findPlaneById(id);
                if(!plane.isPresent()){
                    System.out.println("Plane not found! ");
                }else{
                    planeServiceImp.deletePlaneById(plane.get().getId());
                    System.out.println("Deleted successfully! ");
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
            }
        }
    }
    /*CHECK PLANE INFO */
    public void checkPlaneById(){
        Scanner sc = new Scanner(System.in);
        String id = "";
        while(true){
            infoController.showIds();
            System.out.println("Enter plane ID: ");
            try{
                id = sc.nextLine().toUpperCase();
                Optional<Plane> plane = planeServiceImp.findPlaneById(id);
                if(!plane.isPresent()){
                    System.out.println("Plane not found ...");
                    sc.nextLine();
                    continue;
                }else{
                    showEnteredDataService.showPlaneEntered(plane.get());
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again!");
            }
        }
    }
}
