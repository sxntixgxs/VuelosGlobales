package com.vuelosglobales.flight.fare.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.vuelosglobales.flight.fare.application.service.FareService;

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
}
