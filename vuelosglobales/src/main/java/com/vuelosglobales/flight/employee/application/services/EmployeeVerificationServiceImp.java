package com.vuelosglobales.flight.employee.application.services;

import java.util.List;

import com.vuelosglobales.flight.employee.domain.ports.in.EmployeeOperations;
import com.vuelosglobales.flight.employee.domain.ports.in.EmployeeVerificationService;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;

public class EmployeeVerificationServiceImp implements EmployeeVerificationService{
    private final EmployeeVerificationRepository verificationRepository;

    public EmployeeVerificationServiceImp(EmployeeVerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public List<String> getAirports() {
        return verificationRepository.getAirport();
    }

    @Override
    public List<String> getAirlines() {
        return verificationRepository.getAirline();
    }

    @Override
    public List<String> getCountries() {
        return verificationRepository.getCountry();
    }

    @Override
    public List<String> getEmployees() {
        return verificationRepository.getEmployee();
    }
    
}
