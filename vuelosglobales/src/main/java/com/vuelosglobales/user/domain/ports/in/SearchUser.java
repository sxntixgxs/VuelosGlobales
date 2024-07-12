package com.vuelosglobales.user.domain.ports.in;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;

public interface SearchUser {
    Optional<User> searchUser(String id);//this method will be used on the others ports to know if exists that user or no and how to handle it.
}
