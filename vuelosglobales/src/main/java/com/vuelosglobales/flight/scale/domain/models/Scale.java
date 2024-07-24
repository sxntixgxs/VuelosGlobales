package com.vuelosglobales.flight.scale.domain.models;

import java.sql.Date;

public class Scale {
    int id;
    int idScaleCity;
    int idTrip;
    Date date;//date.valueOf("YYYY-MM-DD")
    public Scale(int id, int idScaleCity, int idTrip, Date date) {
        this.id = id;
        this.idScaleCity = idScaleCity;
        this.idTrip = idTrip;
        this.date = date;
    }
    public Scale(int idScaleCity, int idTrip, Date date) {
        this.idScaleCity = idScaleCity;
        this.idTrip = idTrip;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdScaleCity() {
        return idScaleCity;
    }
    public void setIdScaleCity(int idScaleCity) {
        this.idScaleCity = idScaleCity;
    }
    public int getIdTrip() {
        return idTrip;
    }
    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
