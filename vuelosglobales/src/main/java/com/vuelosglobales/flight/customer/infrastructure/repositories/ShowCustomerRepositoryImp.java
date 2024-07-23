package com.vuelosglobales.flight.customer.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vuelosglobales.flight.customer.domain.ports.out.ShowCustomerRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class ShowCustomerRepositoryImp implements ShowCustomerRepository{
    private final DBConnection dbConnection;

    public ShowCustomerRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<String> getCustomerList(String id) {
        String query = "SELECT C.id,CONCAT(C.name,' ',C.surname) AS name, C.age, TP.typeDoc FROM customer C INNER JOIN typeDocument TP ON C.idTypeDoc = TP.id WHERE id = ?";
        List<String> customerList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                customerList.add(resultSet.getString("TP.typeDoc"));
                customerList.add(resultSet.getString("C.id"));
                customerList.add(resultSet.getString("name"));
                customerList.add(Integer.toString(resultSet.getInt("C.age")));
            }
            return customerList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query getCustomerList "+e.getMessage(),e);
        }
    }
}
