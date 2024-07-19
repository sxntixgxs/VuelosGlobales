// package com.vuelosglobales;

// import java.sql.Date;
// import java.util.Optional;
// import java.util.Scanner;

// import com.vuelosglobales.plane.application.services.DateValidatorImp;
// import com.vuelosglobales.plane.application.services.InfoService;
// import com.vuelosglobales.plane.application.services.PlaneServiceImp;
// import com.vuelosglobales.plane.domain.models.Plane;
// import com.vuelosglobales.plane.domain.ports.out.PlaneRepository;
// import com.vuelosglobales.plane.domain.ports.out.SpecificRepository;
// import com.vuelosglobales.plane.infrastructure.controllers.InfoController;
// import com.vuelosglobales.plane.infrastructure.controllers.PlaneController;
// import com.vuelosglobales.plane.infrastructure.repositories.PlaneRepositoryImp;
// import com.vuelosglobales.plane.infrastructure.repositories.SpecificRepositoryImp;
// import com.vuelosglobales.user.application.services.AuthServiceImpl;
// import com.vuelosglobales.user.application.services.SearchUserImpl;
// import com.vuelosglobales.user.domain.ports.in.AuthService;
// import com.vuelosglobales.user.domain.ports.in.SearchUser;
// import com.vuelosglobales.user.domain.ports.out.UserRepository;
// import com.vuelosglobales.user.infrastructure.config.DBConnection;
// import com.vuelosglobales.user.infrastructure.controllers.UserController;
// import com.vuelosglobales.user.infrastructure.repositories.UserRepositoryImp;

// public class Main {
//     private static final Scanner sc = new Scanner(System.in);
//     public static void main(String[] args) {

//         DBConnection dbConnection = new DBConnection();
//         UserRepositoryImp userRepositoryImp = new UserRepositoryImp(dbConnection);
//         AuthService authService = new AuthServiceImpl(userRepositoryImp);
//         UserController userController = new UserController(userRepositoryImp, authService);
//         PlaneRepositoryImp planeRepository = new PlaneRepositoryImp(dbConnection);
//         SpecificRepositoryImp specificRepository = new SpecificRepositoryImp(dbConnection);
//         // PlaneRepository planeRepository = new PlaneRepository(dbConnection);


//         InfoService infoService = new InfoService(specificRepository);
//         PlaneServiceImp planeServiceImp = new PlaneServiceImp(planeRepository);
//         DateValidatorImp dateValidatorImp = new DateValidatorImp();
//         InfoController infoController = new InfoController(infoService);
//         PlaneController planeController = new PlaneController(planeServiceImp, dateValidatorImp,infoController);
//         while(true){
//             start(userController,planeController);
//             System.out.println("Do you want to perform another action? Y/N: ");
//             String continueOption = sc.next().trim().toLowerCase();
//             if(!continueOption.equals("y")){
//                 break;
//             }
//             sc.nextLine();
//         }
        
//         sc.close();

//     }
        
//     public static Optional<Integer> login(UserController userController){
//         // Scanner sc = new Scanner(System.in);
//         System.out.println("Login ~ VuelosGlobales");
//         System.out.println("Type your ID: ");
//         String id = sc.nextLine();
//         System.out.println("Type your password: ");
//         String password = sc.nextLine();
//         Optional<Integer> roleId = userController.login(id, password);
//         return roleId;
//     }
//     public static void start(UserController userController,PlaneController planeController){
//         Optional<Integer> roleId = login(userController);
//         if(roleId.isPresent()){
//             System.out.println("Login successful. Role ID: " + roleId.get());
//             displayMenu(roleId.get(),planeController);
//         } else {
//             System.out.println("Login failed.");
//         }
//     }
//     public static void displayMenu(int roleId, PlaneController planeController){
//         switch (roleId) {
//             case 1:
//                 int choice = adminMenu();
//                 switch (choice) {
//                     case 1:
//                         int planeChoice = planeMenu();
//                         switch (planeChoice) {
//                             case 1:

//                                 planeController.createPlane();
//                                 break;
//                             case 2: 
//                                 planeController.updatePlane();
//                                 break;
//                             case 3:
//                                 planeController.deletePlaneById();
//                                 break;
//                             case 4:
//                                 Plane plane = planeController.findPlaneById();
//                                 if(plane!=null){
//                                     System.out.println("Plane ID: "+plane.getId());
//                                     System.out.println("Capacity: "+plane.getCapacity());
//                                     System.out.println("Fabrication DATE: "+plane.getFabrication());
//                                     System.out.println("");
//                                 }else{
//                                     System.out.println("Plane not found");
//                                 }
//                                 break;

//                             default:
//                             System.out.println("Invalid option.");
//                                 break;
//                         }
//                         break;
                
//                     default:
//                         System.out.println("Invalid option. ");
//                         break;
//                 }
//                 break;
        
//             default:
//                 break;
//         }
//     }
//     public static int adminMenu(){
//         // Scanner sc = new Scanner(System.in);
//         //PLANE
//         System.out.println("1. Plane actions");

//         // System.out.println("5. Add crew");
//         // System.out.println("6. Check crew assing");

//         // System.out.println("7. Check trip information");
//         // System.out.println("8. Assign plane to trip");
//         // System.out.println("9. Update trip information");
//         // System.out.println("10. Delete trip");
//         // System.out.println("11. Check trip scales");

//         // System.out.println("12. Add airport");
//         // System.out.println("13. Check airport information");
//         // System.out.println("14. Update airport Information");
//         // System.out.println("15. Delete airport");

//         // System.out.println("16. Update scale information");
//         // System.out.println("17. Delete scale");

//         // System.out.println("18. Add flight fare");
//         // System.out.println("19. Update flight fare information");
//         // System.out.println("20. Delete flight fare");
//         // System.out.println("21. Check flight fare");

//         // System.out.println("22. Add document type");
//         // System.out.println("23. Update document type");
//         // System.out.println("24. Delete document type");
//         // System.out.println("25. Check document type");

//         int choice = sc.nextInt();
//         sc.nextLine();
//         // sc.close();
//         return choice;
//     }
//     public static int planeMenu(){
//         // Scanner sc = new Scanner(System.in);
//         int choice;
//         while(true){
//             System.out.println("1. Add plane");
//             System.out.println("2. Update plane information");
//             System.out.println("3. Delete plane");
//             System.out.println("4. Check plane information");
//             choice = sc.nextInt();
//             if(choice>4){
//                 System.out.println("Invalid option\nTry Again!");
//             }else{
//                 break;
//             }
//         }return choice;
//     }
// }







package com.vuelosglobales;

import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.plane.application.services.DateValidatorImp;
import com.vuelosglobales.plane.application.services.InfoService;
import com.vuelosglobales.plane.application.services.PlaneServiceImp;
import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.infrastructure.controllers.InfoController;
import com.vuelosglobales.plane.infrastructure.controllers.PlaneController;
import com.vuelosglobales.plane.infrastructure.repositories.PlaneRepositoryImp;
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

        InfoService infoService = new InfoService(specificRepository);
        PlaneServiceImp planeServiceImp = new PlaneServiceImp(planeRepository);
        DateValidatorImp dateValidatorImp = new DateValidatorImp();
        InfoController infoController = new InfoController(infoService);
        PlaneController planeController = new PlaneController(planeServiceImp, dateValidatorImp, infoController);

        while (true) {
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

        sc.close();
    }

    public static Optional<Integer> login(UserController userController) {
        System.out.println("Login ~ VuelosGlobales");
        System.out.println("For login press 1\nIf you want close the program press any key");
        int exit = sc.nextInt();
        if(exit!=1){
            return Optional.empty();
        }
        sc.nextLine();
        System.out.println("Type your ID: ");
        String id = sc.nextLine();
        System.out.println("Type your password: ");
        String password = sc.nextLine();
        
        Optional<Integer> roleId = userController.login(id, password);
        return roleId;
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
                                Plane plane = planeController.findPlaneById();
                                if (plane != null) {
                                    System.out.println("Plane ID: " + plane.getId());
                                    System.out.println("Capacity: " + plane.getCapacity());
                                    System.out.println("Fabrication DATE: " + plane.getFabrication());
                                    System.out.println("");
                                } else {
                                    System.out.println("Plane not found.");
                                }
                                break;
                            default:
                                System.out.println("Invalid option.");
                                break;
                        }
                        break;
                    case 2:
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
        System.out.println("2. Exit");
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
