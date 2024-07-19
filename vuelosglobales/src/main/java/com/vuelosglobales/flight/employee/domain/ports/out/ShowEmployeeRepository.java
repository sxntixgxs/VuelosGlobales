package com.vuelosglobales.flight.employee.domain.ports.out;

import com.vuelosglobales.flight.employee.domain.models.Employee;

public interface ShowEmployeeRepository {
    void showEmployee(Employee employee);
}
