package com.vuelosglobales;

import java.util.Scanner;

import com.vuelosglobales.user.application.services.AuthServiceImpl;
import com.vuelosglobales.user.application.services.SearchUserImpl;
import com.vuelosglobales.user.domain.ports.in.AuthService;
import com.vuelosglobales.user.domain.ports.in.SearchUser;
import com.vuelosglobales.user.domain.ports.out.UserRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;
import com.vuelosglobales.user.infrastructure.controllers.UserController;
import com.vuelosglobales.user.infrastructure.repositories.UserRepositoryImp;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        UserRepositoryImp userRepositoryImp = new UserRepositoryImp(dbConnection);
        UserController userController = new UserController(userRepositoryImp);
        SearchUser searchUser = new SearchUserImpl(userRepositoryImp);
        AuthService authService = new AuthServiceImpl(searchUser);
        start(authService);


    }
        
    public static boolean login(AuthService authService){
        Scanner sc = new Scanner(System.in);
        System.out.println("Login ~ VuelosGlobales");
        System.out.println("Type your ID: ");
        String id = sc.nextLine();
        System.out.println("Type your email: ");
        String email = sc.nextLine();
        boolean check = authService.authenticate(id, email);
        return check;
    }
    public static void start(AuthService authService){
        boolean check = login(authService);
        if(check){
            System.out.println("Login successfully");
        }else{
            System.out.println("nope");
        }
    }
    }
