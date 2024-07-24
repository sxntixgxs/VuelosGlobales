package com.vuelosglobales.flight.reservation.infrastructure.controllers;

import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.reservation.application.services.ReservationService;
import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.domain.ports.in.ReservationOperations;

public class ReservationController{
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    public void makeReservation(Reservation reservation){
        reservationService.createReservation(reservation);
    }
    public void checkReservation(){
        Scanner sc = new Scanner(System.in);
        reservationService.findReservationById(sc.nextInt());
    }
}
