package com.vuelosglobales.user.domain.ports.in;

public interface AuthService {
    boolean authenticate(String id, String email);
}
