package com.vuelosglobales.flight.employee.domain.ports.out;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Employee;

public interface EmployeeRepository {
    Optional<Employee> saveEmployee(Employee employee);
    Optional<Employee> findEmployeeById(String id);
    Optional<Employee> updateEmployee(Employee employee);
    void deleteEmployee(String id);
}
