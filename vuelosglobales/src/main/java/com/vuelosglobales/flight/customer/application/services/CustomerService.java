package com.vuelosglobales.flight.customer.application.services;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.customer.domain.models.Customer;
import com.vuelosglobales.flight.customer.domain.ports.in.CustomerOperations;
import com.vuelosglobales.flight.customer.domain.ports.in.ShowCustomer;
import com.vuelosglobales.flight.customer.domain.ports.in.ShowDocTypes;
import com.vuelosglobales.flight.customer.domain.ports.out.CustomerRepository;
import com.vuelosglobales.flight.customer.domain.ports.out.ShowCustomerRepository;
import com.vuelosglobales.flight.customer.domain.ports.out.ShowDocTypesRepository;

public class CustomerService implements CustomerOperations,ShowDocTypes,ShowCustomer{
    private final CustomerRepository customerRepository;
    private final ShowDocTypesRepository showDocTypesRepository;
    private final ShowCustomerRepository showCustomerRepository;
    public CustomerService(CustomerRepository customerRepository,ShowDocTypesRepository showDocTypesRepository,ShowCustomerRepository showCustomerRepository) {
        this.customerRepository = customerRepository;
        this.showDocTypesRepository = showDocTypesRepository;
        this.showCustomerRepository = showCustomerRepository;
    }

    @Override
    public Optional<Customer> addCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = findCustomerById(customer.getId()); //verificar que exista actualmente en la db
        if(optionalCustomer.isPresent()){
            return Optional.empty(); //caso en el que ese ID de cliente ya está registrado en la DB
        }else{
            return customerRepository.addCustomer(customer);
        }
    }

    @Override
    public Optional<Customer> findCustomerById(String idCustomer) {
        return customerRepository.findCustomerById(idCustomer);
    }

    @Override
    public Optional<Customer> updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = findCustomerById(customer.getId());
        if(optionalCustomer.isPresent()){//verificar si está en la DB
            return customerRepository.updateCustomer(customer);
        }else{
            //si no existe, se podria añadir desde aqui, pero lo mejor es devolver un empty y desde el controlador realizar esta operacion
            return Optional.empty();
        }
    }
    // esto realmente va en el controlador, no es un servicio
    // @Override
    // public void showCustomer(String idCustomer) {
    //     Optional<Customer> optionalCustomer = findCustomerById(idCustomer);
    //     if(optionalCustomer.isPresent()){
    //         System.out.println("Name: "+optionalCustomer.get().getName()+" "+optionalCustomer.get().getSurname());
    //         System.out.println("Age: "+optionalCustomer.get().getAge());
    //         System.out.println("ID: "+optionalCustomer.get().getId());
    //     }else{
    //         System.out.println("Customer does not exist");
    //     }
    // }

    @Override
    public List<String> showDocTypes() {
        return showDocTypesRepository.getAllDocTypes();
    }

    @Override
    public List<String> showCustomer(String id) {
        return showCustomerRepository.getCustomerList(id);
    }

}
