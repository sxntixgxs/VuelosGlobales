package com.vuelosglobales.flight.scale.infrastructure.controllers;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.scale.application.services.ScaleService;
import com.vuelosglobales.flight.scale.domain.models.Scale;

public class ScaleController {
    private final ScaleService scaleService;
    private static final Scanner sc = new Scanner(System.in);

    public ScaleController(ScaleService scaleService) {
        this.scaleService = scaleService;
    }
    public void checkScalesOfTrip(){
        while(true){
            System.out.println("Enter the trip id: ");
            try{
                int tripId = sc.nextInt();
                List<List<String>> wrap = scaleService.findScalesOfTrip(tripId);
                if(wrap.size()<1){
                    System.out.println("Invalid input, try again ");
                    sc.nextLine();
                    continue;
                }else{
                    scaleService.showScales(wrap);
                    sc.nextLine();
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again ");
                sc.nextLine();
                continue;
            }
        }
    }
    public void scaleUpdate() {
        try {
            int scaleId = 0;
            int tripId = 0;
            int depCityId = 0;

            while(true){
                System.out.println("Enter the scale ID to update: ");
                scaleId = sc.nextInt();
                if(scaleService.getScalesInt().contains(scaleId)){
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }
            }
            while(true){
                System.out.println("Enter the new trip ID: ");
                
                tripId = sc.nextInt();
                if(scaleService.getTripIdList().contains(tripId)){
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
            }

            System.out.println("Enter the new date (YYYY-MM-DD): ");
            String dateStr = sc.nextLine();
            Date date = Date.valueOf(dateStr);

            Scale scale = new Scale(scaleId, depCityId, tripId, date);
            Optional<Scale> updatedScale = scaleService.updateScale(scale);

            if (updatedScale.isPresent()) {
                System.out.println("Scale updated successfully.");
            } else {
                System.out.println("Failed to update the scale. Please check the provided details.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            sc.nextLine();
        }
    }
    public void scaleDelete(){
        while(true){
            System.out.println("Enter the trip id: ");
            try{
                int tripId = sc.nextInt();
                if(!scaleService.deleteScale(tripId)){
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
    }
}
