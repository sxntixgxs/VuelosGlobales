package com.vuelosglobales.flight.employee.domain.ports.in;

import java.util.List;

public interface EmployeeVerificationService {
    List<String> getAirports();
    List<String> getAirlines();
    List<String> getCountries();
    List<String> getEmployees();
}
