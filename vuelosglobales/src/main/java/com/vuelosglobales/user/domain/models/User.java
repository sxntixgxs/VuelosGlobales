package com.vuelosglobales.user.domain.models;

public class User extends Person {
    private String password;
    private int idRol;
    public User(String id, String name, String surname, String email,String password, int idRol) {
        super(id, name, surname, email);
        this.idRol = idRol;
    }
    public int getIdRol() {
        return idRol;
    }
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
