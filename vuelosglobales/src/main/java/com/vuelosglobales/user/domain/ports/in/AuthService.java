package com.vuelosglobales.user.domain.ports.in;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;

public interface AuthService {
    Optional<Integer> authenticate(String id, String password);
    int rolType(User user);
}
