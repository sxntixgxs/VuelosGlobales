package com.vuelosglobales.flight.customer.infrastructure.controllers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vuelosglobales.flight.customer.application.services.CustomerService;
import com.vuelosglobales.flight.customer.domain.models.Customer;

public class CustomerController {
    private final CustomerService customerService;
    private final Scanner sc = new Scanner(System.in);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<String> addCustomer() {
        int idType = 0;
        String id = "";
        String name = "";
        String surname = "";
        int age = 0;

        System.out.println("Enter customer information: ");

        // Obtener el tipo de documento
        while (true) {
            System.out.println("Select an id type: ");
            System.out.println(customerService.showDocTypes());
            customerService.showDocTypes().forEach(System.out::println);
            System.out.println("Enter customer id type: ");
            try {
                idType = sc.nextInt();
                if (customerService.showDocTypes().size() < idType || idType < 1) {
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again ");
                sc.nextLine();
                continue;
            }
        }

        // Obtener el ID
        sc.nextLine(); // Consumir la nueva línea
        while (true) {
            System.out.println("Enter id number: ");
            id = sc.nextLine();
            if (id.length() <= 20 && !id.contains(" ")) {
                break;
            } else {
                System.out.println("Invalid input, try again ");
            }
        }

        // Obtener el nombre
        while (true) {
            System.out.println("Enter customer name (without spaces): ");
            name = sc.nextLine();
            if (name.length() <= 20 && !name.contains(" ")) {
                break;
            } else {
                System.out.println("Invalid input, try again ");
            }
        }

        // Obtener el apellido
        while (true) {
            System.out.println("Enter customer surname: ");
            surname = sc.nextLine();
            if (surname.length() <= 20 && !surname.contains(" ")) {
                break;
            } else {
                System.out.println("Invalid input, try again ");
            }
        }

        // Obtener la edad
        while (true) {
            System.out.println("Enter customer age: ");
            try {
                age = sc.nextInt();
                if (age > 100 || age < 1) {
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again ");
                sc.nextLine();
                continue;
            }
        }

        Customer customer = new Customer(id, idType, name, surname, age);
        if (customerService.addCustomer(customer).isPresent()) {
            System.out.println("Customer " + customer.getId() + " saved successfully");
            return Optional.of(customer.getId());
        } else {
            System.out.println("Failed to save customer");
            return Optional.empty();
        }
    }

    public void updateCustomer() {

        // Obtener el ID del cliente a actualizar
        System.out.println("Enter customer ID to update: ");
        String id = sc.nextLine();
        Optional<Customer> existingCustomer = customerService.findCustomerById(id);

        if (existingCustomer.isEmpty()) {
            System.out.println("Customer not found.");
            return;
        }

        Customer customer = existingCustomer.get();
        int idType = customer.getIdTypeDoc();
        String name = customer.getName();
        String surname = customer.getSurname();
        int age = customer.getAge();

        System.out.println("Updating customer: " + customer);

        // Obtener el tipo de documento
        while (true) {
            System.out.println("Select an id type: ");
            customerService.showDocTypes().forEach(System.out::println);
            System.out.println("Enter customer id type: ");
            try {
                idType = sc.nextInt();
                if (customerService.showDocTypes().size() < idType || idType < 1) {
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again ");
                sc.nextLine();
                continue;
            }
        }

        // Obtener el nombre
        sc.nextLine(); // Consumir la nueva línea
        while (true) {
            System.out.println("Enter customer name (without spaces): ");
            name = sc.nextLine();
            if (name.length() <= 20 && !name.contains(" ")) {
                break;
            } else {
                System.out.println("Invalid input, try again ");
            }
        }

        // Obtener el apellido
        while (true) {
            System.out.println("Enter customer surname: ");
            surname = sc.nextLine();
            if (surname.length() <= 20 && !surname.contains(" ")) {
                break;
            } else {
                System.out.println("Invalid input, try again ");
            }
        }

        // Obtener la edad
        while (true) {
            System.out.println("Enter customer age: ");
            try {
                age = sc.nextInt();
                if (age > 100 || age < 1) {
                    System.out.println("Invalid input, try again");
                    sc.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again ");
                sc.nextLine();
                continue;
            }
        }

        Customer updatedCustomer = new Customer(id, idType, name, surname, age);
        Optional<Customer> optionalCustomer = customerService.updateCustomer(updatedCustomer);
        if (optionalCustomer.isPresent()) {
            System.out.println("Customer " + updatedCustomer.getId() + " updated successfully");
        } else {
            System.out.println("Failed to update customer");
        }
    }
    public void checkCustomer(){
        String customerId = "";
        while(true){
            System.out.println("Enter the customer id");
            customerId = sc.nextLine();
            Optional<Customer> optionalCustomer = customerService.findCustomerById(customerId);
            if(optionalCustomer.isPresent()){
                customerService.showCustomer(customerId).forEach(System.out::println);
                // sc.nextLine();
                break;
            }else{
                System.out.println("Customer not found");
                continue;
            }
        }
    }
}
