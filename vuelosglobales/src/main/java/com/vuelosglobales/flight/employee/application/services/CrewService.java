package com.vuelosglobales.flight.employee.application.services;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Crew;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;

public class CrewService {
    private final EmployeeRepository employeeRepository;

    public CrewService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Optional<Crew> createCrew(String idPilot,String idCopilot, String crewLeader, String crewAssistant, String crewAssistant2){
        //validaciones
        if(employeeRepository.findEmployeeById(idPilot).isPresent() && 
            employeeRepository.findEmployeeById(idCopilot).isPresent() &&
            employeeRepository.findEmployeeById(crewLeader).isPresent() &&
            employeeRepository.findEmployeeById(crewAssistant).isPresent() &&
            employeeRepository.findEmployeeById(crewAssistant2).isPresent()){
                Crew crew = new Crew(idPilot, idCopilot, crewLeader, crewAssistant, crewAssistant2);
                return Optional.of(crew);
            }else{
                return Optional.empty();
            }
    }
    public void assignCrewToTrip(){
    public Optional<Crew> findCrewByTrip(){
    }
    }
}
