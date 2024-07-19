package com.vuelosglobales.flight.employee.application.services;

import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.ShowEmployeeRepository;

public class ShowEmployeeService {
    private final ShowEmployeeRepository showEmployee;

    public ShowEmployeeService(ShowEmployeeRepository showEmployee) {
        this.showEmployee = showEmployee;
    }
    public void showEmployee(Employee emp){
        showEmployee.showEmployee(emp);
    }
}
