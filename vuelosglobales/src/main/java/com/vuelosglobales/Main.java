package com.vuelosglobales;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.customer.application.services.CustomerService;
import com.vuelosglobales.flight.customer.domain.models.Customer;
import com.vuelosglobales.flight.customer.domain.ports.out.ShowCustomerRepository;
import com.vuelosglobales.flight.customer.domain.ports.out.ShowDocTypesRepository;
import com.vuelosglobales.flight.customer.infrastructure.controllers.CustomerController;
import com.vuelosglobales.flight.customer.infrastructure.repositories.CustomerRepositoryImp;
import com.vuelosglobales.flight.customer.infrastructure.repositories.ShowCustomerRepositoryImp;
import com.vuelosglobales.flight.customer.infrastructure.repositories.ShowDocTypesRepositoryImp;
import com.vuelosglobales.flight.employee.application.services.CrewService;
import com.vuelosglobales.flight.employee.application.services.EmployeeVerificationServiceImp;
import com.vuelosglobales.flight.employee.application.services.ShowEmployeeService;
import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;
import com.vuelosglobales.flight.employee.infrastructure.controllers.CrewController;
import com.vuelosglobales.flight.employee.infrastructure.controllers.VerificationController;
import com.vuelosglobales.flight.employee.infrastructure.repositories.CrewRepositoryImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.EmployeeRepositoryImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.EmployeeVerifyRepoImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.ShowEmployeeRepositoryImp;
import com.vuelosglobales.flight.fare.application.service.FareService;
import com.vuelosglobales.flight.fare.domain.models.Fare;
import com.vuelosglobales.flight.fare.infrastructure.controllers.FareController;
import com.vuelosglobales.flight.fare.infrastructure.repositories.FareRepositoryImp;
import com.vuelosglobales.flight.reservation.application.services.ReservationService;
import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.infrastructure.controllers.ReservationController;
import com.vuelosglobales.flight.reservation.infrastructure.repositories.ReservationRepositoryImp;
import com.vuelosglobales.flight.trip.application.service.TripService;
import com.vuelosglobales.flight.trip.infrastructure.controllers.TripController;
import com.vuelosglobales.flight.trip.infrastructure.repositories.TripRepositoryImp;
import com.vuelosglobales.plane.application.services.DateValidatorImp;
import com.vuelosglobales.plane.application.services.InfoService;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.application.services.ShowEnteredDataService;
import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.infrastructure.controllers.InfoController;
import com.vuelosglobales.plane.infrastructure.controllers.PlaneController;
import com.vuelosglobales.plane.infrastructure.repositories.PlaneRepositoryImp;
import com.vuelosglobales.plane.infrastructure.repositories.ShowDataRepoImp;
import com.vuelosglobales.plane.infrastructure.repositories.SpecificRepositoryImp;
import com.vuelosglobales.user.application.services.AuthServiceImpl;
import com.vuelosglobales.user.application.services.SearchUserImpl;
import com.vuelosglobales.user.domain.ports.in.AuthService;
import com.vuelosglobales.user.infrastructure.config.DBConnection;
import com.vuelosglobales.user.infrastructure.controllers.UserController;
import com.vuelosglobales.user.infrastructure.repositories.UserRepositoryImp;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        UserRepositoryImp userRepositoryImp = new UserRepositoryImp(dbConnection);
        AuthService authService = new AuthServiceImpl(userRepositoryImp);
        UserController userController = new UserController(userRepositoryImp, authService);

        // Plane dependencies
        PlaneRepositoryImp planeRepository = new PlaneRepositoryImp(dbConnection);
        SpecificRepositoryImp specificRepository = new SpecificRepositoryImp(dbConnection);
        ShowDataRepoImp showDataRepo = new ShowDataRepoImp(specificRepository);
        PlaneServiceImp planeService = new PlaneServiceImp(planeRepository);
        DateValidatorImp dateValidator = new DateValidatorImp();
        InfoService infoService = new InfoService(specificRepository);
        InfoController infoController = new InfoController(infoService);
        ShowEnteredDataService showEnteredDataService = new ShowEnteredDataService(showDataRepo);
        PlaneController planeController = new PlaneController(planeService, dateValidator,infoController,showEnteredDataService);
        SearchUserImpl searchUser = new SearchUserImpl(userRepositoryImp);
        // Employee dependencies
        // ShowEmployeeService showEmployeeService = new ShowEmployeeService(showE);
        EmployeeRepositoryImp employeeRepository = new EmployeeRepositoryImp(dbConnection);
        EmployeeVerifyRepoImp verificationRepository = new EmployeeVerifyRepoImp(dbConnection);
        EmployeeVerificationServiceImp employeeVerificationServiceImp = new EmployeeVerificationServiceImp(verificationRepository);
        ShowEmployeeRepositoryImp showEmployeeRepository = new ShowEmployeeRepositoryImp(verificationRepository,searchUser);
        ShowEmployeeService showEmployeeService = new ShowEmployeeService(showEmployeeRepository);
        // Trip dependencies
        CrewRepositoryImp crewRepository = new CrewRepositoryImp(dbConnection);
        CrewService crewService = new CrewService(showEmployeeService,employeeRepository,crewRepository); 
        TripRepositoryImp tripRepository = new TripRepositoryImp(dbConnection);
        TripService tripService = new TripService(tripRepository);
        VerificationController verificationController = new VerificationController(employeeVerificationServiceImp);
        CrewController crewController = new CrewController(crewService, employeeRepository, verificationController, showEmployeeService, searchUser);
        TripController tripController = new TripController(crewService, tripService, showEnteredDataService, planeService,crewController);

        //Customer dependencies
        CustomerRepositoryImp customerRepository = new CustomerRepositoryImp(dbConnection);
        ShowDocTypesRepositoryImp showDocTypesRepository = new ShowDocTypesRepositoryImp(dbConnection);
        ShowCustomerRepositoryImp showCustomerRepository = new ShowCustomerRepositoryImp(dbConnection);
        CustomerService customerService = new CustomerService(customerRepository, showDocTypesRepository, showCustomerRepository);
        CustomerController customerController = new CustomerController(customerService);


        //Fare dependencies
        FareRepositoryImp fareRepository = new FareRepositoryImp(dbConnection);
        FareService fareService = new FareService(fareRepository);
        FareController fareController = new FareController(fareService);

        //Reservation dependencies
        ReservationRepositoryImp reservationRepository = new ReservationRepositoryImp(dbConnection);
        ReservationService reservationService = new ReservationService(reservationRepository);
        ReservationController reservationController = new ReservationController(reservationService);

        while (true) {
            System.out.println("For login press x");
            String start = sc.nextLine().toUpperCase();
            if (!start.equals("X")) {
                break;
            } else {
                Optional<Integer> roleId = login(userController);
                if (roleId.isPresent()) {
                    System.out.println("Login successful. Role ID: " + roleId.get());
                    boolean flag = false;
                    while (!flag) {
                        boolean continueSession = displayMenu(roleId.get(), planeController,tripController,customerController,fareController,reservationController);
                        if(!continueSession) {
                             flag = true; // Exit the inner loop to log in again or exit the program //cambios
                         }else{
                            System.out.println("Do you want to perform another action? (yes/no): ");
                            String continueOption = sc.next().trim().toLowerCase();
                            if (!continueOption.equals("yes")) {
                                flag = true; // Exit the inner loop to log in again or exit the program //cambios
                            }
                         }

                        sc.nextLine(); // Consume newline
                    }
                } else {
                    System.out.println("Login failed.");
                    System.out.println("Do you want to try logging in again? (yes/no): ");
                    String retryLogin = sc.next().trim().toLowerCase();
                    if (!retryLogin.equals("yes")) {
                        break; // Exit the main loop to close the program
                    }
                    sc.nextLine(); // Consume newline
                }
            }
        }

        sc.close();
    }
    public static Optional<Integer> login(UserController userController) {
        System.out.println("Login ~ VuelosGlobales");
        sc.nextLine(); // Consume newline
        System.out.println("Type your ID: ");
        String id = sc.nextLine();
        System.out.println("Type your password: ");
        String password = sc.nextLine();
        return userController.login(id, password);
    }

    public static boolean displayMenu(int roleId, PlaneController planeController, TripController tripController, CustomerController customerController,FareController fareController,ReservationController reservationController) {
        switch (roleId) {
            case 1:
                boolean continueSession = true;
                while (continueSession) {
                    int choice = adminMenu();
                    switch (choice) {
                        case 1: // plane
                            int planeChoice = planeMenu();
                            switch (planeChoice) {
                                case 1:
                                    planeController.createPlane();
                                    break;
                                case 2:
                                    planeController.updatePlane();
                                    break;
                                case 3:
                                    planeController.deletePlaneById();
                                    break;
                                case 4:
                                    planeController.checkPlaneById();
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                                    break;
                            }
                            break;
                        case 2: // trip
                            /*
                             * 1. mostrar trips disponibles
                             * 2. elegir trayecto
                             * 3. mostrar todos los empleados disponibles
                             * 4. elegir empleados
                             * 5. guardar en DB
                             * 6. confirmar action UI
                             */
                            int tripChoice = tripMenu();
                            switch (tripChoice) {
                                case 1:
                                    tripController.assignCrewToTrip();
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                            break;
                        case 10:
                            System.out.println("Exiting the program...");
                            continueSession = false;
                            break; // Exit the session
                        default:
                            System.out.println("Invalid menu option.");
                            break;
                    }
                }
                break;
            case 4: // New sales case at the same level as case 1
                System.out.println("Sales role identified.");
                boolean continueSalesSession = true;
                while (continueSalesSession) {
                    int salesChoice = salesMenu();
                    switch (salesChoice) {
                        case 1:
                            Optional<String> idCustomer = customerController.addCustomer();// deuelve el idCustomer que se va a añadir al trayecto!
                            int idTrip = tripController.selectTrip();
                            int idFare = fareController.selectFare(); //devuelve el idFare que selecciono el cl
                            Reservation reservation  = new Reservation(idCustomer.get(), idTrip, idFare);
                            reservationController.makeReservation(reservation);
                            System.out.println("Reservation successfuly");
                            break;
                        case 2://consultar cliente
                            customerController.checkCustomer();
                            break;
                        case 10:
                            System.out.println("Exiting the program...");
                            continueSalesSession = false;
                            break; // Exit the session
                        default:
                            System.out.println("Invalid menu option.");
                            break;
                    }
                }
                break;
            default:
                System.out.println("Invalid role ID.");
                break;
        }
        return true; // Continue the session
    }
    
    

    public static int salesMenu(){

        int choice;
        while(true){

            System.out.println("1. Create flight reservation");
            System.out.println("2. Check customer information");
            System.out.println("10. Exit");
            System.out.println("more options ...");
            try {
                Scanner sc = new Scanner(System.in);
                if(sc.hasNextInt()){
                    System.out.println("Enter your choice ");
                    choice = sc.nextInt();
                    if(choice<1 || choice > 4){
                        System.out.println("Invalid input, try again");
                        continue;
                    }else{
                        sc.close();
                        break;
                    }
                }else{
                    System.out.println("Invalid input 1, try again");
                    sc.nextLine();
                    continue;
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid input 2 try again ");
                continue;
            }
        }
        sc.close();
        return choice;
    }
    public static int adminMenu() {
        System.out.println("1. Plane actions");
        System.out.println("2. Trip actions");
        System.out.println("10. Exit");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        return choice;
    }

    public static int planeMenu() {
        int choice;
        while (true) {
            System.out.println("1. Add plane");
            System.out.println("2. Update plane information");
            System.out.println("3. Delete plane");
            System.out.println("4. Check plane information");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid option\nTry Again!");
            } else {
                break;
            }
        }
        return choice;
    }
    public static int tripMenu(){
        while(true){
            System.out.println("1. Assign crew to trip");
            try{
                int choice = sc.nextInt();
                if(choice<1){
                    System.out.println("Invalid option, try again");
                    sc.nextLine();
                    continue;
                }else{
                    return choice;
                }

            }catch(InputMismatchException e){
                System.out.println("Invalid input, try again");
                sc.nextLine();
                continue;
            }
        }
    }
}
