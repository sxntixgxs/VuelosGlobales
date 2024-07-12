package com.vuelosglobales.user.application.services;

import java.util.Optional;

import com.vuelosglobales.user.domain.exceptions.UserNotFoundException;
import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.in.CreateUser;
import com.vuelosglobales.user.domain.ports.in.DeleteUser;
import com.vuelosglobales.user.domain.ports.in.SearchUser;
import com.vuelosglobales.user.domain.ports.in.UpdateUser;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class UserService implements CreateUser,DeleteUser,SearchUser,UpdateUser{
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(User user) {
        if(searchUser(user.getId()).isPresent()){
            return userRepository.updateUser(user);
        }else{
            throw new UserNotFoundException("User with ID "+user.getId()+" not found.");
        }

    }
    @Override
    public Optional<User> searchUser(String id) {
        return userRepository.findUser(id);
    }

    @Override
    public void deleteUser(String id) {
        if(searchUser(id).isPresent()){
            userRepository.deleteUser(id);
        }else{
            throw new UserNotFoundException("User with ID "+id+" not found.");
        }
    }

    @Override
    public User createUser(User user) {
        return user;
    }
    
}
