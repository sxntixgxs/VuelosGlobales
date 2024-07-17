package com.vuelosglobales.plane.domain.models;

public class Plane {
    String id;
    int capacity;
    String fabrication; //date
    int idStatus;
    int idAirline;
    int idModel;
    public Plane(String id, int capacity, String fabrication, int idStatus, int idAirline, int idModel) {
        this.id = id;
        this.capacity = capacity;
        this.fabrication = fabrication;
        this.idStatus = idStatus;
        this.idAirline = idAirline;
        this.idModel = idModel;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public String getFabrication() {
        return fabrication;
    }
    public void setFabrication(String fabrication) {
        this.fabrication = fabrication;
    }
    public int getIdStatus() {
        return idStatus;
    }
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    public int getIdAirline() {
        return idAirline;
    }
    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }
    public int getIdModel() {
        return idModel;
    }
    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

}
