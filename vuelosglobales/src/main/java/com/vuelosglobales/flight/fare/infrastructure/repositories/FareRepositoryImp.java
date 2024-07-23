package com.vuelosglobales.flight.fare.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;

import com.vuelosglobales.flight.fare.domain.models.Fare;
import com.vuelosglobales.flight.fare.domain.ports.out.FareRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class FareRepositoryImp implements FareRepository{
    private final DBConnection dbConnection;

    public FareRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Fare> addFare(Fare fare) {
        String query = "INSERT INTO flightFare(id,name,details,amount)VALUES(?,?,?,?)";
        try(
            Connection connection =dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, fare.getId());
            preparedStatement.setString(2, fare.getName());
            preparedStatement.setString(3,fare.getDetails());
            preparedStatement.setInt(4, fare.getAmount());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(fare);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Fail trying insert fare "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Fare> updateFare(Fare fare) {
        String query = "UPDATE flightFare SET name=?,details=?,amount=? WHERE id=?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, fare.getName());
            preparedStatement.setString(2, fare.getDetails());
            preparedStatement.setInt(3, fare.getAmount());
            preparedStatement.setInt(4, fare.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(fare);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Fail trying update fare "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Fare> getFareById(int id) {
        String query = "SELECT id,name,details,amount FROM flightFare WHERE id=?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Fare foundFare = new Fare(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("details"),
                    resultSet.getInt("amount")
                );
                return Optional.of(foundFare);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to recovery fare info "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAllFares() {
        String query = "SELECT id,name,details,amount FROM flightFare";
        List<String> faresList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                faresList.add("ID -> "+resultSet.getInt("id")+" Fare -> "+resultSet.getString("name")+" Amount -> "+resultSet.getInt("amount")+"\n detalis -> "+resultSet.getString("details"));
            }
            return faresList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed recovering all fares "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deleteFare(int id) {
        String query = "DELETE FROM flightFare WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff>=1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Fail trying delete fare "+e.getMessage(),e);
        }
    }
    
}
