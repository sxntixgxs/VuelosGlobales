package com.vuelosglobales.user.domain.ports.in;



import com.vuelosglobales.user.domain.models.User;

public interface UpdateUser {
    User updateUser(User user);
}
