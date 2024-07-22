package com.vuelosglobales.flight.employee.infrastructure.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.employee.application.services.CrewService;
import com.vuelosglobales.flight.employee.application.services.EmployeeService;
import com.vuelosglobales.flight.employee.application.services.ShowEmployeeService;
import com.vuelosglobales.flight.employee.domain.models.Crew;
import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.CrewRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;
import com.vuelosglobales.flight.employee.infrastructure.repositories.CrewRepositoryImp;
import com.vuelosglobales.user.application.services.SearchUserImpl;
import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.infrastructure.controllers.UserController;
import com.vuelosglobales.user.infrastructure.repositories.UserRepositoryImp;

public class CrewController {
    private final CrewService crewService;
    private final EmployeeRepository employeeRepository;
    private final VerificationController verificationController;
    private final ShowEmployeeService showEmployeeService;
    private final SearchUserImpl searchUserImpl;
    public CrewController(CrewService crewService, EmployeeRepository employeeRepository, VerificationController verificationController, ShowEmployeeService showEmployeeService,SearchUserImpl searchUserImpl) {
        this.crewService = crewService;
        this.employeeRepository = employeeRepository;
        this.verificationController = verificationController;
        this.showEmployeeService = showEmployeeService;
        this.searchUserImpl = searchUserImpl;
    }
    
    public void createCrew(){
        Scanner sc = new Scanner(System.in);
        String idPilot = "";
        String idCopilot = "";
        String idCrewLeader = "";
        String idCrewAssistant = "";
        String idCrewAssistant2 = "";
        while(true){
            System.out.println("Enter the Pilot ID : ");
            verificationController.showEmployees();
            idPilot = sc.nextLine();
            Optional<Employee> optionalPilot = employeeRepository.findEmployeeById(idPilot);
            if(optionalPilot.isPresent()){
                System.out.println("This is your Pilot select");
                showEmployeeService.showEmployee(optionalPilot.get());
                sc.nextLine();
                break;
            }else{
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the Copilot ID: ");
            verificationController.showEmployees();
            idCopilot = sc.nextLine();
            Optional<Employee> optionalCopilot = employeeRepository.findEmployeeById(idCopilot);
            if(optionalCopilot.isPresent()){
                System.out.println("This is your Copilot select");
                showEmployeeService.showEmployee(optionalCopilot.get());
                sc.nextLine();
                break;
            }else{
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the Crew Leader ID: ");
            verificationController.showEmployees();
            idCrewLeader = sc.nextLine();
            Optional<Employee> optionalCrewLeader = employeeRepository.findEmployeeById(idCrewLeader);
            if(optionalCrewLeader.isPresent()){
                Optional<User> optionalUser = searchUserImpl.searchUser(optionalCrewLeader.get().getIdUser());
                if(optionalUser.get().getIdRol()==2 || optionalUser.get().getIdRol()==3){
                    System.out.println("This is your Crew Leader select ");
                    showEmployeeService.showEmployee(optionalCrewLeader.get());
                    sc.nextLine();
                    break;
                }else{
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }

            }else{
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }while(true){
            System.out.println("Enter the Crew Assistant #1 ID: ");
            verificationController.showEmployees();
            idCrewAssistant = sc.nextLine();
            Optional<Employee> optionalCrewAssistant = employeeRepository.findEmployeeById(idCrewAssistant);
            if(optionalCrewAssistant.isPresent()){
                Optional<User> optionalUser = searchUserImpl.searchUser(optionalCrewAssistant.get().getIdUser());
                if(optionalUser.get().getIdRol()==2||optionalUser.get().getIdRol()==3){
                    System.out.println("This is your Crew Assistant #1 select");
                    showEmployeeService.showEmployee(optionalCrewAssistant.get());
                    break;
                }else{
                    System.out.println("Invalid input, try again");
                    continue;
                }
            }else{
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }while(true){
            System.out.println("Enter the Crew Assistant #2 ID: ");
            verificationController.showEmployees();
            idCrewAssistant2 = sc.nextLine();
            Optional<Employee> optionalCrewAssistant2 = employeeRepository.findEmployeeById(idCrewAssistant2);
            if(optionalCrewAssistant2.isPresent()){
                Optional<User> optionalUser = searchUserImpl.searchUser(optionalCrewAssistant2.get().getIdUser());
                if(optionalUser.get().getIdRol()==2||optionalUser.get().getIdRol()==3){
                    System.out.println("This is your Crew Assistant #1 select");
                    showEmployeeService.showEmployee(optionalCrewAssistant2.get());
                    break;
                }else{
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
            }else{
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }  Optional<Integer> optionalCrewID = crewService.createCrew(idPilot, idCopilot, idCrewLeader, idCrewAssistant, idCrewAssistant2);
        if(optionalCrewID.isPresent()){
            System.out.println("Crew saved with ID -> "+optionalCrewID.get());
        }
    }
    public void findCrewById(){
        Scanner sc = new Scanner(System.in);
        int idCrew = 0;
        List<Employee> crewList = new ArrayList<>();
        while(true){
            try{
                idCrew = sc.nextInt();
                crewService.showCrew(idCrew);
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
            }
        }
    }
}
