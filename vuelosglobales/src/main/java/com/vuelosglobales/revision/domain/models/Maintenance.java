package com.vuelosglobales.revision.domain.models;

import java.sql.Date;

public class Maintenance {
    int id;
    String idEmployee;
    String idPlane;
    Date date;
    String description;
    public Maintenance(String idEmployee, String idPlane, Date date, String description) {
        this.idEmployee = idEmployee;
        this.idPlane = idPlane;
        this.date = date;
        this.description = description;
    }
    public Maintenance(int id, String idEmployee, String idPlane, Date date, String description) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idPlane = idPlane;
        this.date = date;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIdEmployee() {
        return idEmployee;
    }
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }
    public String getIdPlane() {
        return idPlane;
    }
    public void setIdPlane(String idPlane) {
        this.idPlane = idPlane;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
