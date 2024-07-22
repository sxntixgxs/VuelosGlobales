package com.vuelosglobales.flight.employee.infrastructure.repositories;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.ShowEmployeeRepository;
import com.vuelosglobales.user.application.services.SearchUserImpl;
import com.vuelosglobales.user.domain.models.User;

public class ShowEmployeeRepositoryImp implements ShowEmployeeRepository{
    private final EmployeeVerificationRepository verificationRepository;
    private final SearchUserImpl searchUserImpl;
    public ShowEmployeeRepositoryImp(EmployeeVerificationRepository verificationRepository,SearchUserImpl searchUserImpl) {
        this.verificationRepository = verificationRepository;
        this.searchUserImpl = searchUserImpl;
    }

    @Override
    public void showEmployee(Employee employee) {
        System.out.println("ID -> "+employee.getIdUser());
        System.out.println("Airport  -> "+verificationRepository.getAirport().get(employee.getIdAirport()-1));
        Optional<User> optionalUser = searchUserImpl.searchUser(employee.getIdUser());
        if(optionalUser.isPresent()){
            System.out.println(optionalUser.get().getName()+" "+optionalUser.get().getSurname()+" "+verificationRepository.getRol().get(optionalUser.get().getIdRol()));
            // System.out.println(verificationRepository.getRol());
            // System.out.println(verificationRepository.getRol().get(optionalUser.get().getIdRol()));
        }
        // System.out.println(employee.getIdAirline());
        // System.out.println(verificationRepository.getAirline());
        System.out.println(verificationRepository.getAirline().get(employee.getIdAirline()-1));
        System.out.println("Country -> "+verificationRepository.getCountry().get(employee.getIdCountry()-1));
        // System.out.println("Airline -> :"+verificationRepository.getAirline().get(employee.getIdAirline()-1));
        // System.out.println(employee.getIdCountry());
        // System.out.println("Country -> "+verificationRepository.getCountry().get(employee.getIdCountry()));
    }
    
}
