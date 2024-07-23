package com.vuelosglobales.flight.customer.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosglobales.flight.customer.domain.models.Customer;
import com.vuelosglobales.flight.customer.domain.ports.in.ShowDocTypes;
import com.vuelosglobales.flight.customer.domain.ports.out.CustomerRepository;
import com.vuelosglobales.flight.customer.domain.ports.out.ShowDocTypesRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class CustomerRepositoryImp implements CustomerRepository{
    private final DBConnection dbConnection;

    public CustomerRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Customer> addCustomer(Customer customer) {
        String query = "INSERT INTO customer(id,idTypeDoc,name,surname,age)VALUES(?,?,?,?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, customer.getId());//pasamos id del objeto customer
            preparedStatement.setInt(2, customer.getIdTypeDoc());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getSurname());
            preparedStatement.setInt(5, customer.getAge());
            try{
                int rowsAff = preparedStatement.executeUpdate();
                if(rowsAff>=1){
                    return Optional.of(customer);
                }else{
                    throw new SQLException("DB error: -adding customer- ");
                }
            }catch(SQLException e){
                System.out.println("Error with SQL query "+e.getMessage());
                throw new RuntimeException("Failed to add customer on DB: "+e.getMessage(),e);
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to add customer on DB: "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Customer> findCustomerById(String idCustomer) {
        String query = "SELECT id,idTypeDoc,name,surname,age FROM customer WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Customer foundCustomer = new Customer(
                    resultSet.getString("id"),
                    resultSet.getInt("idTypeDoc"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age")
                 );
                return Optional.of(foundCustomer);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to retreive customer "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Customer> updateCustomer(Customer customer) {
        String query = "UPDATE customer SET idTypeDoc = ?, name = ?, surname = ?, age = ? WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, customer.getIdTypeDoc());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setInt(4, customer.getAge());
            preparedStatement.setString(5, customer.getId());//este es el id del cliente, que va al where
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return findCustomerById(customer.getId());
            }else{
                throw new SQLException("Failed to update customer");
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to execute query update customer"+e.getMessage(),e);
        }
    }





}
