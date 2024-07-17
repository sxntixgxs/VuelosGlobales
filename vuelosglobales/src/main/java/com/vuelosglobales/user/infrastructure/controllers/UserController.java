package com.vuelosglobales.user.infrastructure.controllers;

import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.user.application.services.AuthServiceImpl;
import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.in.AuthService;
import com.vuelosglobales.user.infrastructure.repositories.UserRepositoryImp;

public class UserController {
    private final UserRepositoryImp userRepositoryImp;
    private final AuthService authService;

    public UserController(UserRepositoryImp userRepositoryImp,AuthService authService) {
        this.userRepositoryImp = userRepositoryImp;
        this.authService = authService;
    }
    public void createUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String id = sc.nextLine();
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter surname: ");
        String surname = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Enter ID Rol: ");
        int idRol = sc.nextInt();
        User user = new User(id,name,surname,email,password,idRol);
        User check = userRepositoryImp.saveUser(user);
        System.out.println("User created / saved sucessfully");
        sc.close(); 
    }
    public Optional<User> findUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String id = sc.nextLine();
        Optional check = userRepositoryImp.findUser(id);
        if(check.isPresent()){
            System.out.println("User exists!");
            sc.close();
            return check;
        }else{
            System.out.println("User doesn't exists");
            sc.close();
            return Optional.empty();
        }
    }
    public void deleteUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String id = sc.nextLine();
        userRepositoryImp.deleteUser(id);
    }
    public void updateUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ID: ");
        String id = sc.nextLine();
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter surname: ");
        String surname = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        System.out.println("Enter ID Rol: ");
        int idRol = sc.nextInt();
        User user = new User(id,name,surname,email,password,idRol);
        User check = userRepositoryImp.updateUser(user);
        if(check.getName()!=null){
            System.out.println("User updated sucessfully!");
        }
        sc.close();
    }
    public Optional<Integer> login(String id, String password){
        return authService.authenticate(id, password);
    }

}
