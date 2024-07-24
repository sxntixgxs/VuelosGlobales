package com.vuelosglobales;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.customer.application.services.CustomerService;
import com.vuelosglobales.flight.customer.infrastructure.controllers.CustomerController;
import com.vuelosglobales.flight.customer.infrastructure.repositories.CustomerRepositoryImp;
import com.vuelosglobales.flight.customer.infrastructure.repositories.ShowCustomerRepositoryImp;
import com.vuelosglobales.flight.customer.infrastructure.repositories.ShowDocTypesRepositoryImp;
import com.vuelosglobales.flight.employee.application.services.CrewService;
import com.vuelosglobales.flight.employee.application.services.EmployeeVerificationServiceImp;
import com.vuelosglobales.flight.employee.application.services.ShowEmployeeService;
import com.vuelosglobales.flight.employee.infrastructure.controllers.CrewController;
import com.vuelosglobales.flight.employee.infrastructure.controllers.VerificationController;
import com.vuelosglobales.flight.employee.infrastructure.repositories.CrewRepositoryImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.EmployeeRepositoryImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.EmployeeVerifyRepoImp;
import com.vuelosglobales.flight.employee.infrastructure.repositories.ShowEmployeeRepositoryImp;
import com.vuelosglobales.flight.fare.application.service.FareService;
import com.vuelosglobales.flight.fare.infrastructure.controllers.FareController;
import com.vuelosglobales.flight.fare.infrastructure.repositories.FareRepositoryImp;
import com.vuelosglobales.flight.reservation.application.services.ReservationService;
import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.infrastructure.controllers.ReservationController;
import com.vuelosglobales.flight.reservation.infrastructure.repositories.ReservationRepositoryImp;
import com.vuelosglobales.flight.scale.application.services.ScaleService;
import com.vuelosglobales.flight.scale.domain.models.Scale;
import com.vuelosglobales.flight.scale.domain.ports.out.ScaleRepository;
import com.vuelosglobales.flight.scale.infrastructure.controllers.ScaleController;
import com.vuelosglobales.flight.scale.infrastructure.repositories.ScaleRepositoryImp;
import com.vuelosglobales.flight.trip.application.service.DateValidatorService;
import com.vuelosglobales.flight.trip.application.service.TripService;
import com.vuelosglobales.flight.trip.domain.ports.in.DateValidator;
import com.vuelosglobales.flight.trip.infrastructure.controllers.TripController;
import com.vuelosglobales.flight.trip.infrastructure.repositories.TripRepositoryImp;
import com.vuelosglobales.plane.application.services.DateValidatorImp;
import com.vuelosglobales.plane.application.services.InfoService;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.application.services.ShowEnteredDataService;
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
        
        // Initialize controllers
        UserController userController = initUserController(dbConnection);
        PlaneController planeController = initPlaneController(dbConnection);
        TripController tripController = initTripController(dbConnection);
        CustomerController customerController = initCustomerController(dbConnection);
        FareController fareController = initFareController(dbConnection);
        ReservationController reservationController = initReservationController(dbConnection);
        ScaleController scaleController = initScaleController(dbConnection);

        boolean running = true;
        while (running) {
            Optional<Integer> roleId = login(userController);
            if (roleId.isPresent()) {
                System.out.println("Login successful. Role ID: " + roleId.get());
                boolean continueSession = true;
                while (continueSession) {
                    continueSession = displayMenu(roleId.get(), planeController, tripController, customerController, fareController, reservationController,scaleController);
                }
            } else {
                System.out.println("Login failed.");
            }
            System.out.println("Do you want to try again? (y/n)");
            try {
                if (sc.hasNextLine()) {
                    String retry = sc.nextLine().toLowerCase();
                    running = retry.equals("y");
                } else {
                    running = false;
                }
            } catch (NoSuchElementException e) {
                System.out.println("No se encontró línea de entrada.");
                running = false;
            }
        }

        sc.close();
    }
    private static ScaleController initScaleController(DBConnection dbConnection){
        ScaleRepositoryImp scaleRepositoryImp = new ScaleRepositoryImp(dbConnection);
        ScaleService scaleService = new ScaleService(scaleRepositoryImp);
        return new ScaleController(scaleService);
    }
    private static UserController initUserController(DBConnection dbConnection) {
        UserRepositoryImp userRepositoryImp = new UserRepositoryImp(dbConnection);
        AuthService authService = new AuthServiceImpl(userRepositoryImp);
        return new UserController(userRepositoryImp, authService);
    }

    private static PlaneController initPlaneController(DBConnection dbConnection) {
        PlaneRepositoryImp planeRepository = new PlaneRepositoryImp(dbConnection);
        SpecificRepositoryImp specificRepository = new SpecificRepositoryImp(dbConnection);
        ShowDataRepoImp showDataRepo = new ShowDataRepoImp(specificRepository);
        PlaneServiceImp planeService = new PlaneServiceImp(planeRepository);
        DateValidatorImp dateValidator = new DateValidatorImp();
        InfoService infoService = new InfoService(specificRepository);
        InfoController infoController = new InfoController(infoService);
        ShowEnteredDataService showEnteredDataService = new ShowEnteredDataService(showDataRepo);
        return new PlaneController(planeService, dateValidator, infoController, showEnteredDataService);
    }

    private static TripController initTripController(DBConnection dbConnection) {
        EmployeeRepositoryImp employeeRepository = new EmployeeRepositoryImp(dbConnection);
        EmployeeVerifyRepoImp verificationRepository = new EmployeeVerifyRepoImp(dbConnection);
        EmployeeVerificationServiceImp employeeVerificationServiceImp = new EmployeeVerificationServiceImp(verificationRepository);
        ShowEmployeeRepositoryImp showEmployeeRepository = new ShowEmployeeRepositoryImp(verificationRepository, new SearchUserImpl(new UserRepositoryImp(dbConnection)));
        ShowEmployeeService showEmployeeService = new ShowEmployeeService(showEmployeeRepository);
        DateValidatorService dateValidator = new DateValidatorService();


        CrewRepositoryImp crewRepository = new CrewRepositoryImp(dbConnection);
        CrewService crewService = new CrewService(showEmployeeService, employeeRepository, crewRepository);
        TripRepositoryImp tripRepository = new TripRepositoryImp(dbConnection);
        TripService tripService = new TripService(tripRepository);
        VerificationController verificationController = new VerificationController(employeeVerificationServiceImp);
        CrewController crewController = new CrewController(crewService, employeeRepository, verificationController, showEmployeeService, new SearchUserImpl(new UserRepositoryImp(dbConnection)));
        return new TripController(crewService, tripService, new ShowEnteredDataService(new ShowDataRepoImp(new SpecificRepositoryImp(dbConnection))), new PlaneServiceImp(new PlaneRepositoryImp(dbConnection)), crewController,dateValidator);
    }

    private static CustomerController initCustomerController(DBConnection dbConnection) {
        CustomerRepositoryImp customerRepository = new CustomerRepositoryImp(dbConnection);
        ShowDocTypesRepositoryImp showDocTypesRepository = new ShowDocTypesRepositoryImp(dbConnection);
        ShowCustomerRepositoryImp showCustomerRepository = new ShowCustomerRepositoryImp(dbConnection);
        CustomerService customerService = new CustomerService(customerRepository, showDocTypesRepository, showCustomerRepository);
        return new CustomerController(customerService);
    }

    private static FareController initFareController(DBConnection dbConnection) {
        FareRepositoryImp fareRepository = new FareRepositoryImp(dbConnection);
        FareService fareService = new FareService(fareRepository);
        return new FareController(fareService);
    }

    private static ReservationController initReservationController(DBConnection dbConnection) {
        ReservationRepositoryImp reservationRepository = new ReservationRepositoryImp(dbConnection);
        ReservationService reservationService = new ReservationService(reservationRepository);
        return new ReservationController(reservationService);
    }

    public static Optional<Integer> login(UserController userController) {
        System.out.println("Login ~ VuelosGlobales");
        System.out.println("Type your ID: ");
        String id = sc.nextLine();
        System.out.println("Type your password: ");
        String password = sc.nextLine();
        return userController.login(id, password);
    }

    public static boolean displayMenu(int roleId, PlaneController planeController, TripController tripController, CustomerController customerController, FareController fareController, ReservationController reservationController,ScaleController scaleController) {
        switch (roleId) {
            case 1:
                return handleAdminMenu(planeController, tripController,fareController,scaleController);
            case 4:
                return handleSalesMenu(customerController, tripController, fareController, reservationController,scaleController);
            default:
                System.out.println("Invalid role ID.");
                return false;
        }
    }

    public static boolean handleAdminMenu(PlaneController planeController, TripController tripController,FareController fareController,ScaleController scaleController) {
        while (true) {
            int choice = adminMenu();
            if (choice == 10) {
                return false;
            }
            switch (choice) {
                case 1:
                    handlePlaneMenu(planeController,tripController);
                    break;
                case 2:
                    handleTripMenu(tripController);
                    break;
                case 3:
                    handleFareMenu(fareController);
                case 4:
                    handleScaleMenu(scaleController);
                    break;
                case 10:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static boolean handleSalesMenu(CustomerController customerController, TripController tripController, FareController fareController, ReservationController reservationController,ScaleController scaleController) {
        while (true) {
            int salesChoice = salesMenu();
            if (salesChoice == 10) {
                return false;
            }
            switch (salesChoice) {
                case 1:
                    Optional<String> idCustomer = customerController.addCustomer();
                    int idTrip = tripController.selectTrip();
                    int idFare = fareController.selectFare();
                    Reservation reservation = new Reservation(idCustomer.get(), idTrip, idFare);
                    reservationController.makeReservation(reservation);
                    System.out.println("Reservation successfully made.");
                    break;
                case 2:
                    customerController.checkCustomer();
                    break;
                case 3:
                    reservationController.checkReservation();
                    break;
                case 4:
                    customerController.addCustomer();
                    break;
                case 5:
                    customerController.updateCustomer();
                case 6:
                    reservationController.deleteReservation();
                case 7:
                    handleScaleMenu(scaleController);
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
        }
    }

    public static void handlePlaneMenu(PlaneController planeController,TripController tripController) {
        while (true) {
            int planeChoice = planeMenu();
            switch (planeChoice) {
                case 1:
                    planeController.createPlane();
                    break;
                case 2:
                    planeController.updatePlane();
                    sc.nextLine();
                    break;
                case 3:
                    planeController.deletePlaneById();
                    break;
                case 4:
                    planeController.checkPlaneById();
                    break;
                case 5:
                    tripController.checkTrip();
                    break;
                case 10:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }break;
        }
    }

    public static void handleTripMenu(TripController tripController) {
        while (true) {
            int tripChoice = tripMenu();
            switch (tripChoice) {
                case 1:
                    tripController.assignCrewToTrip();
                    break;
                case 2:
                    tripController.checkTrip();
                    break;
                case 3:
                    tripController.assignPlaneToTrip();
                    break;
                case 4:
                    tripController.updateTrip();
                    break;
                case 5:
                    tripController.deleteTrip();
                    break;
                case 10:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }break;
        }
    }
    public static void handleFareMenu(FareController fareController){
        while (true){
            int fareChoide = fareMenu();
            switch (fareChoide) {
                case 1:
                    fareController.addFare();
                    break;
                case 2:
                    fareController.updateFare();
                    break;
                case 3:
                    fareController.deleteFare();
                    break;
                case 4:
                    fareController.showFareById();
                    break;
                case 10:
                    break;
                default:
                    break;
            }break;
        }
    }
    public static void handleScaleMenu(ScaleController scaleController) {
    while (true) {
        int scaleChoice = scaleMenu();
        switch (scaleChoice) {
            case 1:
                scaleController.checkScalesOfTrip();
                break;
            case 2:
                scaleController.scaleUpdate();
                break;
            case 3:
                scaleController.scaleDelete();
                break;
            case 10:
                return;
            default:
                System.out.println("Invalid choice, please try again.");
                break;
        }
    }
}
    public static int adminMenu() {
        System.out.println("1. Plane actions");
        System.out.println("2. Trip actions");
        System.out.println("3. Fare actions");
        System.out.println("4. Scale actions");
        System.out.println("10. Exit");
        return getInputChoice(1, 10);
    }

    public static int planeMenu() {
        System.out.println("1. Add plane");
        System.out.println("2. Update plane information");
        System.out.println("3. Delete plane");
        System.out.println("4. Check plane information");
        System.out.println("5. Check trip information");
        System.out.println("10. Exit menu");
        return getInputChoice(1, 10);
    }

    public static int tripMenu() {
        System.out.println("1. Assign crew to trip");
        System.out.println("2. Check trip information");
        System.out.println("3. Assign airplane to trip");
        System.out.println("4. Update trip information");
        System.out.println("5. Delete trip");
        System.out.println("10. Exit");
        return getInputChoice(1, 10);
    }
    public static int fareMenu(){
        System.out.println("1. Register flight fare");
        System.out.println("2. Update flight fare");
        System.out.println("3. Delete flight fare");
        System.out.println("4. Check flight fare");
        System.out.println("10. Exit");
        return getInputChoice(1, 10);
    }
    public static int scaleMenu(){
        System.out.println("1. Check the trip scales");
        System.out.println("2. Update scales information");
        System.out.println("3. Delete scale");
        System.out.println("10. Exit");
        return getInputChoice(1, 10);
    }
    public static int salesMenu() {
        System.out.println("1. Create flight reservation");
        System.out.println("2. Check customer information");
        System.out.println("3. Check reservation information");
        System.out.println("4. Customer register");
        System.out.println("5. Update customer information");
        System.out.println("6. Delete flight reservation");
        System.out.println("7. Scale operations [menu]");
        System.out.println("10. Exit");
        return getInputChoice(1, 10);
    }

    public static int getInputChoice(int min, int max) {
        int choice = -1;

        while (true) {
            try {
                System.out.println("Enter your choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (choice >= min && choice <= max) {
                        break;
                    } else {
                        System.out.println("Please enter a number between " + min + " and " + max + ".");
                    }
                } else {
                    System.out.println("Invalid input, please enter a number.");
                    sc.nextLine(); // Consume invalid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                sc.nextLine(); // Consume invalid input
            }
        }
        return choice;
    }
}
