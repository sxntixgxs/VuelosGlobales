package com.vuelosglobales.user.application.services;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.in.SearchUser;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class SearchUserImpl implements SearchUser{
    private final UserRepository userRepository;

    public SearchUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> searchUser(String id) {
        return userRepository.findUser(id);
    }
}
