package com.vuelosglobales.flight.fare.domain.models;

public class Fare {
    int id;
    String name;
    String details;
    int amount;
    public Fare(int id, String name, String details, int amount) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
