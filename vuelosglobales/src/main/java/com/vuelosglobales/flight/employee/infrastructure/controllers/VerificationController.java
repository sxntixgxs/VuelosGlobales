package com.vuelosglobales.flight.employee.infrastructure.controllers;

import java.util.List;

import com.vuelosglobales.flight.employee.application.services.EmployeeVerificationServiceImp;
import com.vuelosglobales.flight.employee.domain.ports.in.EmployeeVerificationService;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;

public class VerificationController {
    private final EmployeeVerificationServiceImp verificationServiceImp;

    public VerificationController(EmployeeVerificationServiceImp verificationServiceImp) {
        this.verificationServiceImp = verificationServiceImp;
    }
    public void showAirports(){
        List<String> airportList = verificationServiceImp.getAirports();
        airportList.forEach(System.out::println);
    }
    public void showAirlines(){
        List<String> airlineList = verificationServiceImp.getAirlines();
        airlineList.forEach(System.out::println);
    }
    public void showCountries(){
        List<String> countryList = verificationServiceImp.getCountries();
        countryList.forEach(System.out::println);
    }
    public void showEmployees(){
        List<String> employeList = verificationServiceImp.getEmployees();
        employeList.forEach(System.out::println);
    }
}
