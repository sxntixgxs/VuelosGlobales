package com.vuelosglobales.user.domain.ports.out;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;

public interface UserRepository {
    User saveUser(User user);
    Optional<User> findUser(String id);
    void deleteUser(String id);
    User updateUser(User user);
    Optional<User> findUserByPassAndId(String id, String password);
}
