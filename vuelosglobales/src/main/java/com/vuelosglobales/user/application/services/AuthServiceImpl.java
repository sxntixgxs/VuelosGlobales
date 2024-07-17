package com.vuelosglobales.user.application.services;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.in.AuthService;
import com.vuelosglobales.user.domain.ports.in.SearchUser;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Integer> authenticate(String id, String password) {
        Optional<User> user = userRepository.findUserByPassAndId(id, password);
        return user.map(User::getIdRol);
    }

    @Override
    public int rolType(User user) {
        return user.getIdRol();
    }




    
}
