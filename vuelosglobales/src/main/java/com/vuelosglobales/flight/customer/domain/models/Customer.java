package com.vuelosglobales.flight.customer.domain.models;

public class Customer {
    String id;
    int idTypeDoc;
    String name;
    String surname;
    int age;
    public Customer(String id, int idTypeDoc, String name, String surname, int age) {
        this.id = id;
        this.idTypeDoc = idTypeDoc;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getIdTypeDoc() {
        return idTypeDoc;
    }
    public void setIdTypeDoc(int idTypeDoc) {
        this.idTypeDoc = idTypeDoc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
