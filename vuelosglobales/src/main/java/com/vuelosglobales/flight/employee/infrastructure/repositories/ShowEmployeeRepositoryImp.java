package com.vuelosglobales.flight.employee.infrastructure.repositories;

import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.ShowEmployeeRepository;

public class ShowEmployeeRepositoryImp implements ShowEmployeeRepository{
    private final EmployeeVerificationRepository verificationRepository;

    public ShowEmployeeRepositoryImp(EmployeeVerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public void showEmployee(Employee employee) {
        System.out.println("ID -> "+employee.getIdUser());
        System.out.println("Airport  -> "+verificationRepository.getAirport().get(employee.getIdAirport()-1));
        System.out.println("Airline -> "+verificationRepository.getAirline().get(employee.getIdAirline()-1));
        System.out.println("Country -> "+verificationRepository.getCountry().get(employee.getIdCountry()-1));
    }
    
}
