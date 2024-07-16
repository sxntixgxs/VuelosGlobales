package com.vuelosglobales.user.application.services;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.in.AuthService;
import com.vuelosglobales.user.domain.ports.in.SearchUser;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class AuthServiceImpl implements AuthService{
    private final SearchUser searchUser;

    public AuthServiceImpl(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    @Override
    public boolean authenticate(String id, String email) {
        Optional<User> user = searchUser.searchUser(id);
        return user.isPresent();
    }




    
}
