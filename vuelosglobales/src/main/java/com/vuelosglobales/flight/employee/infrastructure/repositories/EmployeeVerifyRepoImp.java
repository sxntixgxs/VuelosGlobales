package com.vuelosglobales.flight.employee.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeVerificationRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class EmployeeVerifyRepoImp implements EmployeeVerificationRepository{
    private final DBConnection dbConnection;
    public EmployeeVerifyRepoImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<String> getAirport() {
        String query = "SELECT A.id,A.name, C.name FROM airport A INNE JOIN city C ON A.idCity = C.id";
        List<String> airportList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String airport = "ID: "+resultSet.getInt("id")+" "+resultSet.getString("A.name")+" "+resultSet.getString("C.name");
                airportList.add(airport);
            }
            return airportList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAirline() {
        String query = "SELECT id, name FROM airline";
        List<String> airlineList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String airline =resultSet.getInt("id")+" "+resultSet.getString("name");
                airlineList.add(airline);
            }
            return airlineList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getCountry() {
        String query = "SELECT id,name FROM country";
        List<String> countryList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String country = resultSet.getInt("id")+" "+resultSet.getString("name");
                countryList.add(country);
            }
            return countryList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB error: "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getEmployee() {
        String query = "SELECT E.idUser,UR.rol FROM employee E INNER JOIN user U ON E.idUser = U.id INNER JOIN userRoles UR ON U.idRol = UR.id";
        List<String> employeeList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String employee ="ID: "+resultSet.getString("E.idUser")+" "+resultSet.getString("UR.rol");
                employeeList.add(employee);
            }
            return employeeList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }
}
