package com.vuelosglobales.flight.employee.domain.ports.out;

import java.util.List;

public interface EmployeeVerificationRepository {
    public List<String> getAirport();
    public List<String> getAirline();
    public List<String> getCountry();
    public List<String> getEmployee();
    public List<String> getRol();
}
