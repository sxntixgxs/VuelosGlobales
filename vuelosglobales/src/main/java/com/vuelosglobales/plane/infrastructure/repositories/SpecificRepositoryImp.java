package com.vuelosglobales.plane.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vuelosglobales.plane.domain.ports.out.SpecificRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class SpecificRepositoryImp implements SpecificRepository{
    private final DBConnection dbConnection;
    public SpecificRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<String> getStatus() {
        String query = "SELECT name FROM status";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                List<String> statusList = new ArrayList<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    String statusName = resultSet.getString("name");
                    statusList.add(statusName);
                }
                return statusList;  
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Failed DB "+e.getMessage(),e);
            }

    }

    @Override
    public List<String> getAirline() {
        String query = "SELECT name FROM airline";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                List<String> airlineList = new ArrayList<>();
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String airlineName = resultSet.getString("name");
                    airlineList.add(airlineName);
                }
                return airlineList;
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB error"+e.getMessage(),e);
            }
    }

    @Override
    public List<String> getModel() {
        String query = "SELECT name FROM model";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            List<String> modelList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String modelName = resultSet.getString("name");
                modelList.add(modelName);
            }
            return modelList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB error"+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getId() {
        String query = "SELECT id FROM plane";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            List<String> idList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String idPlane = resultSet.getString("id");
                idList.add(idPlane);
            }
            return idList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB error "+e.getMessage(),e);
        }
    }
    
}
