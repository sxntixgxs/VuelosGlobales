package com.vuelosglobales.flight.employee.application.services;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.in.EmployeeOperations;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;
import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class EmployeeService implements EmployeeOperations{
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeService(EmployeeRepository employeeRepository,UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Employee> createEmployee(Employee employee) {
        Optional<User> userOptional = userRepository.findUser(employee.getIdUser());
        if(!userOptional.isPresent()){
            throw new IllegalArgumentException("User does not exists");
        }
        Optional<Employee> employeeOptional = findEmployeeById(employee.getIdUser());
        if(!employeeOptional.isPresent()){
            return employeeRepository.saveEmployee(employee);
        }else{
            throw new IllegalArgumentException("Employee already exists");
        }

    }

    @Override
    public Optional<Employee> findEmployeeById(String id) { 
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteEmployee(id);
    }
}
