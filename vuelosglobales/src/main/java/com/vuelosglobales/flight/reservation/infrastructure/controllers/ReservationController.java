package com.vuelosglobales.flight.reservation.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.reservation.application.services.ReservationService;
import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.domain.ports.in.ReservationOperations;

public class ReservationController{
    private final ReservationService reservationService;
    private final Scanner sc = new Scanner(System.in);
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    public void makeReservation(Reservation reservation){
        reservationService.createReservation(reservation);
    }
    public void checkReservation(){
        while(true){
            System.out.println("Check reservation with: ");
            System.out.println("1. Customer id ");
            System.out.println("2. Trip id");
            try{
                int choice = sc.nextInt();
                if(choice<1 || choice>2){
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
                switch (choice) {
                    case 1:
                        sc.nextLine();//No se puede borrar, está consumiendo el buffer, ni si quiera se muestra en consola
                        checkReservationByCustomerId();
                        break;
                    case 2:
                        System.out.println("case 2");

                        checkReservationByTripId();
                        break;
                    default:
                        break;
                }break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }

        }
    }
    private void checkReservationByReservationId(){
        System.out.println("Enter the reservation id: ");
        int reservationId = sc.nextInt();
        List<String> reservationInfo = reservationService.findReservationById(reservationId);
        if(reservationInfo.size()>1){
            reservationInfo.forEach(System.out::println);
        };
    }
    public void checkReservationByCustomerId(){
        System.out.println("Enter the customer id: ");
        String customerId = sc.nextLine();
        List<List<String>> reservationInfo = reservationService.findReservationByCustomerId(customerId);
        if(reservationInfo.size()<1){
            System.out.println("Reservation not found ...");
            sc.nextLine();
        }else{
            for (List<String> reservation : reservationInfo) {
                reservation.forEach(System.out::println);
                System.out.println("Enter any key to continue ...");
                sc.nextLine();
            }
        }

    }
    public void checkReservationByTripId(){
        System.out.println("Enter the trip id: ");
        int reservationId = sc.nextInt();
        List<List<String>> reservationInfo = reservationService.findReservationByTripId(reservationId);
        for (List<String> reservation : reservationInfo) {
            reservation.forEach(System.out::println);
            System.out.println("Enter any key to continue ...");
            sc.nextLine();
        }
    }
    public void deleteReservation(){
        System.out.println("Enter the reservation id: ");
        int reservationId = sc.nextInt();
        if(reservationService.deleteReservation(reservationId)){
            System.out.println("Reservation "+reservationId+" deleted successfuly");
            sc.nextLine();
        }
    }
}
