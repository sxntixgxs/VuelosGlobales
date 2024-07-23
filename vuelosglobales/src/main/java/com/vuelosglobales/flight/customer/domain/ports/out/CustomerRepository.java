package com.vuelosglobales.flight.customer.domain.ports.out;

import java.util.Optional;

import com.vuelosglobales.flight.customer.domain.models.Customer;

public interface CustomerRepository {
    Optional<Customer> addCustomer(Customer customer);
    Optional<Customer> findCustomerById(String idCustomer);
    Optional<Customer> updateCustomer(Customer customer);
}
