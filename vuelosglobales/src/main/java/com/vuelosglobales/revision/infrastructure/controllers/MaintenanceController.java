package com.vuelosglobales.revision.infrastructure.controllers;

import java.util.Scanner;

import com.vuelosglobales.revision.application.services.MaintenanceService;

public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }
    public void addMaintenance(String idEmployee, String idPlane){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the maintenance date: ");
        Date date = sc.nextLine();
    }
}
