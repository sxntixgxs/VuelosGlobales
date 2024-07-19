package com.vuelosglobales.flight.trip.domain.models;

public class Trip {
    private int id;
    private int idRoute;
    private int idCrew;
    private String date;
    private int idStatus;
    private int idAirplane;
    public Trip(int id, int idRoute, int idCrew, String date, int idStatus,int idAirplane) {
        this.id = id;
        this.idRoute = idRoute;
        this.idCrew = idCrew;
        this.date = date;
        this.idStatus = idStatus;
        this.idAirplane = idAirplane;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdRoute() {
        return idRoute;
    }
    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }
    public int getIdCrew() {
        return idCrew;
    }
    public void setIdCrew(int idCrew) {
        this.idCrew = idCrew;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getIdStatus() {
        return idStatus;
    }
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    public int getIdAirplane() {
        return idAirplane;
    }
    public void setIdAirplane(int idAirplane) {
        this.idAirplane = idAirplane;
    }
}
