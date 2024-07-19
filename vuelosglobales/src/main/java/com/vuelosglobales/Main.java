package com.vuelosglobales;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

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
        PlaneRepositoryImp planeRepository = new PlaneRepositoryImp(dbConnection);
        SpecificRepositoryImp specificRepository = new SpecificRepositoryImp(dbConnection);
        ShowDataRepoImp showDataRepoImp = new ShowDataRepoImp(specificRepository);
        ShowEnteredDataService showEnteredDataService = new ShowEnteredDataService(showDataRepoImp);
        InfoService infoService = new InfoService(specificRepository);
        PlaneServiceImp planeServiceImp = new PlaneServiceImp(planeRepository);
        DateValidatorImp dateValidatorImp = new DateValidatorImp();
        InfoController infoController = new InfoController(infoService);
        PlaneController planeController = new PlaneController(planeServiceImp, dateValidatorImp, infoController, showEnteredDataService);

        while (true) {
            System.out.println("For login press x");
            String start = sc.nextLine().toUpperCase();
            if (!start.equals("X")) {
                break;
            } else {
                Optional<Integer> roleId = login(userController);
                if (roleId.isPresent()) {
                    System.out.println("Login successful. Role ID: " + roleId.get());
                    while (true) {
                        boolean continueSession = displayMenu(roleId.get(), planeController);
                        if (!continueSession) {
                            break; // Exit the inner loop to log in again or exit the program
                        }
                        System.out.println("Do you want to perform another action? (yes/no): ");
                        String continueOption = sc.next().trim().toLowerCase();
                        if (!continueOption.equals("yes")) {
                            break; // Exit the inner loop to log in again or exit the program
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

    public static boolean displayMenu(int roleId, PlaneController planeController) {
        switch (roleId) {
            case 1:
                int choice = adminMenu();
                switch (choice) {
                    case 1:
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
                    case 2:
                        /*
                         * 1. mostrar trips disponibles
                         * 2. elegir trayecto
                         * 3. mostrar todos los empleados disponibles
                         * 4. elegir empleados
                         * 5. guardar en DB
                         * 6. confirmar action UI
                         */
                    case 10:
                        System.out.println("Exiting the program...");
                        return false; // Exit the session
                    default:
                        System.out.println("Invalid menu option.");
                        break;
                }
                break;
            default:
                System.out.println("Invalid role.");
                break;
        }
        return true; // Continue the session
    }

    public static int adminMenu() {
        System.out.println("1. Plane actions");
        System.out.println("2. Assign crew");
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
}
