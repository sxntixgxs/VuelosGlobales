package com.vuelosglobales.flight.employee.infrastructure.controllers;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.employee.application.services.DateValidatorImp;
import com.vuelosglobales.flight.employee.application.services.EmployeeService;
import com.vuelosglobales.flight.employee.application.services.ShowEmployeeService;
import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.in.DateValidator;
import com.vuelosglobales.flight.employee.domain.ports.in.EmployeeVerificationService;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;
import com.vuelosglobales.flight.employee.infrastructure.repositories.EmployeeRepositoryImp;
import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class EmployeeController {
    private final EmployeeService employeeService;
    private final UserRepository userRepository;
    private final VerificationController verificationController;
    private final DateValidatorImp dateValidatorImp;
    private final ShowEmployeeService showEmployeeService;
    public EmployeeController(EmployeeService employeeService, UserRepository userRepository,
            VerificationController verificationController,DateValidatorImp dateValidatorImp,ShowEmployeeService showEmployeeService) {
        this.employeeService = employeeService;
        this.userRepository = userRepository;
        this.verificationController = verificationController;
        this.dateValidatorImp = dateValidatorImp;
        this.showEmployeeService = showEmployeeService;
    }


    /*CREATE EMPLOYEE */
    public void createEmployee(){
        Scanner sc = new Scanner(System.in);
        String userId = "";
        while(true){
            System.out.println("Enter ID: ");
            System.out.println("To close this menu press x");
            userId = sc.nextLine();
            if(userId.equalsIgnoreCase("x")){
                System.out.println("Exiting menu ...");
                break;
            }
            Optional<User> userOptional = userRepository.findUser(userId);
            if(userOptional.isPresent()){
                System.out.println("User currently registered! ");
                break;
            }else{
                System.out.println("User does not exist. Please create the user first in the corresponding menu! ");
                sc.nextLine();
                continue;
            }
        }
        if(!userId.equalsIgnoreCase("x")){
            int idAirport = 0;
            int idAirline = 0;
            int idCountry = 0;
            String admissionDate = "";
            while(true){
                verificationController.showAirports();
                System.out.println("Enter the airport ID: ");
                try {
                    idAirport=sc.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input, try again! ");
                    sc.nextLine();
                    continue;
                }
            }
            while(true){
                verificationController.showAirlines();
                System.out.println("Enter the airline ID: ");
                try{
                    idAirline = sc.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input, try again!");
                    sc.nextLine();
                    continue;
                }
            }
            while(true){
                verificationController.showCountries();
                System.out.println("Enter the country ID: ");
                try{
                    idCountry=sc.nextInt();
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input, try again!");
                    sc.nextLine();
                    continue;
                }
            }
            while(true){
                System.out.println("Enter the admission date: FORMAT YYYY-MM-DD");
                admissionDate = sc.nextLine();
                boolean check = dateValidatorImp.isValid(admissionDate);
                if(check){
                    break;
                }else{
                    System.out.println("Use the correct DATE FORMAT ");
                    sc.nextLine();
                    continue;
                }
            }
            Employee emp = new Employee(userId, idAirport, idAirline, idCountry, admissionDate);
            try {
                showEmployeeService.showEmployee(emp);
                employeeService.createEmployee(emp);
            } catch (Exception e) {
                System.out.println("Error creating employee "+e);
            }
        }
    }


    public void updateEmployee(){
        Scanner sc = new Scanner(System.in);
        String id = "";
        int idAirport = 0;
        int idAirline = 0;
        int idCountry = 0;
        String admissionDate = "";
        while(true){
            System.out.println("Enter employee ID: ");
            id=sc.nextLine();
            Optional<Employee> emp = employeeService.findEmployeeById(id);
            if(!emp.isPresent()){
                System.out.println("Employee not found, try again");
                sc.nextLine();
                continue;
            }else{
                System.out.println("This is the current employee ...");
                showEmployeeService.showEmployee(emp.get());
                break;
            }
        }

        while(true){
            verificationController.showAirports();
            System.out.println("Enter the airport ID: ");
            try{
                idAirport=sc.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            verificationController.showAirlines();
            System.out.println("Enter the airline ID: ");
            try{
                idAirline = sc.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            verificationController.showCountries();
            System.out.println("Enter the country ID: ");
            try{
                idCountry = sc.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
        while(true){
            System.out.println("Enter the admission date: FORMAT YYYY-MM-DD");
            admissionDate = sc.nextLine();
            boolean check = dateValidatorImp.isValid(admissionDate);
            if(check){
                break;
            }else{
                System.out.println("Use the correct DATE FORMAT ");
                sc.nextLine();
                continue;
            }
        }
        Employee employee = new Employee(id, idAirport, idAirline, idCountry, admissionDate);

        try{       
            System.out.println("These are your updates: ");
             showEmployeeService.showEmployee(employee);
             employeeService.updateEmployee(employee);
            }catch(Exception e){
                System.out.println("Error updating employee");
            }

    }

    public void deleteEmployee(){
        Scanner sc = new Scanner(System.in);
        String id = "";
        while(true){
            System.out.println("Enter the employee ID that will be deleted");
            id=sc.nextLine();
            Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
            if(!employeeOptional.isPresent()){
               System.out.println("Employee not found, try again"); 
               sc.nextLine();
               continue;
            }else{
                System.out.println("Deleting ...");
                sc.nextLine();
                employeeService.deleteEmployee(id);
                System.out.println("Employee deleted successfully");
            }
        }
    }
    public void checkEmployee(){
        verificationController.showEmployees();
    }
}
