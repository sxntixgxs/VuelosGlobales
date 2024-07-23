package com.vuelosglobales.flight.reservation.domain.models;

public class Reservation {
    int id;
    String idCustomer;
    int idTrip;
    int idFlightFare;
    //la llave primaria son las 3
    public Reservation(String idCustomer, int idTrip, int idFlightFare) {
        this.idCustomer = idCustomer;
        this.idTrip = idTrip;
        this.idFlightFare = idFlightFare;
    }
    public Reservation(int id,String idCustomer, int idTrip, int idFlightFare) {
        this.id=id;
        this.idCustomer = idCustomer;
        this.idTrip = idTrip;
        this.idFlightFare = idFlightFare;
    }
    public String getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public int getIdFlightFare() {
        return idFlightFare;
    }
    public void setIdFlightFare(int idFlightFare) {
        this.idFlightFare = idFlightFare;
    }

}
