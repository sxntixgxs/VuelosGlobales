package com.vuelosglobales.user.infrastructure.repositories;

import java.util.Optional;

import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.out.UserRepository;

public class UserRepositoryImp implements UserRepository{

    @Override
    public User saveUser(User user) {
        String query = "INSERT INTO user VALUES(id,name,surname,email,idRol) VALUES(?,?,?,?,?);"
        try(Connect);

    }

    @Override
    public Optional<User> findUser(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUser'");
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
    
}
