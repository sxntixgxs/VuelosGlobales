package com.vuelosglobales.flight.customer.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vuelosglobales.flight.customer.domain.ports.out.ShowDocTypesRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class ShowDocTypesRepositoryImp implements ShowDocTypesRepository{
    private final DBConnection dbConnection;

    public ShowDocTypesRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<String> getAllDocTypes() {
        String query = "SELECT id,typeDoc FROM typeDocument";
        List<String> allDocs = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                allDocs.add(resultSet.getInt("id")+" "+resultSet.getString("typeDoc"));
            }
            return allDocs;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query all doc types "+e.getMessage(),e);
        }
    }
}
