package com.vuelosglobales.flight.fare.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.fare.application.service.FareService;
import com.vuelosglobales.flight.fare.domain.models.Fare;

public class FareController {
    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }
    public int selectFare(){
        Scanner sc = new Scanner(System.in);
        int idFareSelected=0;
        /*
         * metodo para seleccionar una tarifa
         * se muestran las tarifas disponibles y se devuelve la seleccion
         */
        while(true){
            System.out.println("FARES AVALIABLES");
            fareService.showAllFares().forEach(System.out::println);//imprimimos la lista de todos las fares, cada string tiene todos los datos
            System.out.println("Type the fare id: ");
            try{
                idFareSelected = sc.nextInt();
                if(idFareSelected>fareService.showAllFares().size() || idFareSelected<1){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    return idFareSelected;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }

    }
    public void showFareById(){
        Scanner sc = new Scanner(System.in);
        int idFareSelected=0;
        while(true){
            System.out.println("Enter the fare id: ");
            try{
                idFareSelected = sc.nextInt();
                Optional<Fare> optionalFare = fareService.findFareById(idFareSelected);
                if(!optionalFare.isPresent()){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    System.out.println("FARE ID: -> "+optionalFare.get().getId());
                    System.out.println("FARE NAME: -> "+optionalFare.get().getName());
                    System.out.println("DETAILS: -> "+optionalFare.get().getDetails());
                    System.out.println("AMOUNT: -> "+optionalFare.get().getAmount());
                    sc.nextLine();
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
    }
    public void deleteFare(){
        Scanner sc = new Scanner(System.in);
        int idFareSelected = 0;
        while(true){
            System.out.println("Enter the fare id: ");
            try{
                idFareSelected = sc.nextInt();
                Optional<Fare> optionalFare = fareService.findFareById(idFareSelected);
                if(!optionalFare.isPresent()){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    if(fareService.deleteFare(idFareSelected)){
                        System.out.println("Fare deleted successfuly");
                        sc.nextLine();
                        break;
                    }else{
                        System.out.println("Invalid input, try again! ");
                        sc.nextLine();
                        continue;
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
    }
    public void updateFare(){
        Scanner sc = new Scanner(System.in);
        int idFare = 0;
        String name = "";
        String details = "";
        int amount = 0;
        while(true){
            System.out.println("Enter the fare id: ");
            try{
                idFare = sc.nextInt();
                Optional<Fare> optionalFare = fareService.findFareById(idFare);
                if(!optionalFare.isPresent()){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    sc.nextLine();
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare name: ");
            try{
                name = sc.nextLine();
                break;
                
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare details: ");
            try{
                details = sc.nextLine();
                break;
                
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare amount: ");
            try{
                amount = sc.nextInt();
                if(amount>20){
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Invalid input, try again ");
                    sc.nextLine();
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        Fare newFare = new Fare(idFare, name, details, amount);
        if(fareService.updateFare(newFare).isPresent()){
            System.out.println("Fare "+name+" updated successfuly! ");
            sc.nextLine();
        }
    }
    public void addFare(){
        Scanner sc = new Scanner(System.in);
        int idFare = 0;
        String name = "";
        String details = "";
        int amount = 0;
        while(true){
            System.out.println("Enter the fare id: ");
            try{
                idFare = sc.nextInt();
                Optional<Fare> optionalFare = fareService.findFareById(idFare);
                if(optionalFare.isPresent()){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }else{
                    sc.nextLine();
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare name: ");
            try{
                name = sc.nextLine();
                break;
                
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare details: ");
            try{
                details = sc.nextLine();
                break;
                
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the fare amount: ");
            try{
                amount = sc.nextInt();
                if(amount>20){
                    break;
                }else{
                    System.out.println("Invalid input, try again ");
                    sc.nextLine();
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again! ");
                sc.nextLine();
                continue;
            }
        }
        Fare newFare = new Fare(idFare, name, details, amount);
        if(fareService.addFare(newFare).isPresent()){
            System.out.println("Fare "+name+" updated successfuly! ");
            sc.nextLine();
        }
    }
}
