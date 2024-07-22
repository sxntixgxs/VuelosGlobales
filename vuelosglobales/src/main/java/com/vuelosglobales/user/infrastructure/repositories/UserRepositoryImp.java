package com.vuelosglobales.user.infrastructure.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.vuelosglobales.user.domain.models.User;
import com.vuelosglobales.user.domain.ports.out.UserRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class UserRepositoryImp implements UserRepository{
    private final DBConnection dbConnection;


    
    public UserRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public User saveUser(User user) {
        String query = "INSERT INTO user(id,name,surname,email,idRol) VALUES(?,?,?,?,?);";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getIdRol());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff>=1){
                return user;
            }else{
                throw new SQLException("Failed to insert");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public Optional<User> findUser(String id) {
        String query = "SELECT id,name,surname,email,password,idRol FROM user WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        User user = new User(resultSet.getString("id"), resultSet.getString("name"), 
                        resultSet.getString("surname"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getInt("idRol"));
                        return Optional.of(user);
                    }else{
                        return Optional.empty();
                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB Error"+e.getMessage(),e);
            }
    }

    @Override
    public void deleteUser(String id) {
        String query = "DELETE FROM user WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    preparedStatement.setString(1, id);
                    int rowsAff = preparedStatement.executeUpdate();
                    if(rowsAff<1){
                        throw new SQLException("Failed to delete");
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
    }

    @Override
    public User updateUser(User user) {
        String query = "UPDATE user SET name=?,surname=?,email=?,idRol=? WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setInt(4, user.getIdRol());
                preparedStatement.setString(5, user.getId());
                int rowsAff = preparedStatement.executeUpdate();
                if(rowsAff==1){
                    return user;
                }else{
                    throw new SQLException("Failed to update user");
                }
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB error"+e.getMessage(),e);
            }
    }

    @Override
    public Optional<User> findUserByPassAndId(String id, String password) {
        String query = "SELECT id,name,surname,email,password,idRol FROM user WHERe id = ? AND password = ?";
        try(Connection connection = dbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                if(resultSet.next()){
                    User user = new User(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("surname"),resultSet.getString("email"),resultSet.getString("password"),resultSet.getInt("idRol"));
                    return Optional.of(user);
                }else{
                    return Optional.empty();
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }
    
}
