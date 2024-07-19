package com.vuelosglobales.flight.employee.domain.models;

import com.vuelosglobales.user.domain.models.User;

public class Employee {
    private String  idUser;
    private int idAirport;
    private int idAirline;
    private int idCountry;
    private String admissionDate;
    public Employee(String idUser, int idAirport, int idAirline, int idCountry, String admissionDate) {
        this.idUser = idUser;
        this.idAirport = idAirport;
        this.idAirline = idAirline;
        this.idCountry = idCountry;
        this.admissionDate = admissionDate;
    }
    public String getIdUser() {
        return idUser;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public int getIdAirport() {
        return idAirport;
    }
    public void setIdAirport(int idAirport) {
        this.idAirport = idAirport;
    }
    public int getIdAirline() {
        return idAirline;
    }
    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }
    public int getIdCountry() {
        return idCountry;
    }
    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
    public String getAdmissionDate() {
        return admissionDate;
    }
    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }
    
}
