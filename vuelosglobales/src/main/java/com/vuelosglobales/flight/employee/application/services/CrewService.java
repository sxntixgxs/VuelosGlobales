package com.vuelosglobales.flight.employee.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.employee.domain.models.Crew;
import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.in.CrewOperations;
import com.vuelosglobales.flight.employee.domain.ports.out.CrewRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;

public class CrewService implements CrewOperations {
    private final ShowEmployeeService showEmployeeService;
    private final EmployeeRepository employeeRepository;
    private final CrewRepository crewRepository;

    public CrewService(ShowEmployeeService showEmployeeService,EmployeeRepository employeeRepository, CrewRepository crewRepository) {
        this.showEmployeeService = showEmployeeService;
        this.employeeRepository = employeeRepository;
        this.crewRepository = crewRepository;
    }
    @Override
    public Optional<Integer> createCrew(String idPilot,String idCopilot, String crewLeader, String crewAssistant, String crewAssistant2){
        //validaciones
        if(employeeRepository.findEmployeeById(idPilot).isPresent() && 
            employeeRepository.findEmployeeById(idCopilot).isPresent() &&
            employeeRepository.findEmployeeById(crewLeader).isPresent() &&
            employeeRepository.findEmployeeById(crewAssistant).isPresent() &&
            employeeRepository.findEmployeeById(crewAssistant2).isPresent()){
                return crewRepository.saveCrew(idPilot, idCopilot, crewLeader, crewAssistant, crewAssistant2);
            }else{
                return Optional.empty();
            }
    }

    @Override
    public boolean assignCrewToTrip(Crew crew, int idtTrip) {
        //solucion q al crear crew me devuelva el id de la crew
        Optional<Integer> idCrew = crewRepository.findIdCrewByComponents(
            crew.getIdPilot(),
            crew.getIdCopilot(),
            crew.getCrewLeader(),
            crew.getCrewAssistant(),
            crew.getCrewAssistant2()
        );
        if(idCrew.isPresent()){
            return crewRepository.setCrewOnTrip(idCrew.get(), idtTrip);
        }else{
            return false;
        }
    }

    @Override
    public Optional<Crew> findCrewById(int idCrew) {
        return crewRepository.findCrewById(idCrew);
    }
    @Override
    public void showCrew(int idCrew) {
        Scanner sc = new Scanner(System.in);
        Optional<Crew> optionalCrew = findCrewById(idCrew);
        if(optionalCrew.isPresent()){
            System.out.println("·························");
            employeeRepository.findEmployeeById(optionalCrew.get().getIdPilot()).ifPresent(showEmployeeService::showEmployee);
            System.out.println("·························");
            sc.nextLine();
            employeeRepository.findEmployeeById(optionalCrew.get().getIdCopilot()).ifPresent(showEmployeeService::showEmployee);
            System.out.println("·························");
            sc.nextLine();
            employeeRepository.findEmployeeById(optionalCrew.get().getCrewLeader()).ifPresent(showEmployeeService::showEmployee);
            System.out.println("·························");
            sc.nextLine();
            employeeRepository.findEmployeeById(optionalCrew.get().getCrewAssistant()).ifPresent(showEmployeeService::showEmployee);
            System.out.println("·························");
            sc.nextLine();
            employeeRepository.findEmployeeById(optionalCrew.get().getCrewAssistant2()).ifPresent(showEmployeeService::showEmployee);
        }
    }
    
}
