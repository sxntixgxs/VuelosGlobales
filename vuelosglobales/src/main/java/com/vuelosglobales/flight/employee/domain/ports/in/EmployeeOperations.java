package com.vuelosglobales.flight.employee.domain.ports.in;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Employee;

public interface EmployeeOperations {
    Employee createEmployee(Employee employee);
    Optional<Employee> findEmployeeById(String id);
    Optional<Employee> updateEmployee(Employee employee);
    void deleteEmployee(String id);
}
