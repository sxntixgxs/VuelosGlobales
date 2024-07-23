package com.vuelosglobales.flight.employee.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.vuelosglobales.flight.employee.domain.models.Employee;
import com.vuelosglobales.flight.employee.domain.ports.out.EmployeeRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class EmployeeRepositoryImp implements EmployeeRepository{
    private final DBConnection dbConnection;

    public EmployeeRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Employee> saveEmployee(Employee employee) {
       String query = "INSERT INTO employee(idUser,idAirport,idAirline,idCountry,admissionDate) VALUES(?,?,?,?,?)";
       Optional<Employee> optionalEmployee;
       
       try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, employee.getIdUser());
                preparedStatement.setInt(2, employee.getIdAirport());
                preparedStatement.setInt(3, employee.getIdAirline());
                preparedStatement.setInt(4, employee.getIdCountry());
                preparedStatement.setString(5, employee.getAdmissionDate());
                
                try{
                    int rowsAff = 0;
                     rowsAff= preparedStatement.executeUpdate();
                
                    if(rowsAff>=1){
                        return Optional.of(employee);
                    }
                }catch(Exception e){
                    System.out.println("ERROR CON LA EJECUCION DEL STATEMENT");
                }
                return Optional.empty();
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB Error"+e.getMessage(),e);
            }
    }

    @Override
    public Optional<Employee> findEmployeeById(String id) {
        String query = "SELECT idUser,idAirport,idAirline,idCountry,admissionDate FROM employee WHERE idUser = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                if(resultSet.next()){
                    Employee employee = new Employee(
                        resultSet.getString("idUser"),
                        resultSet.getInt("idAirport"),
                        resultSet.getInt("idAirline"),
                        resultSet.getInt("idCountry"),
                        resultSet.getString("admissionDate")  
                    );
                    return Optional.of(employee);
                }else{
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) {
        String query = "UPDATE employee SET idAirport=?,idAirline=?,idCountry=?,admissionDate=? WHERE idUser = ? ";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, employee.getIdAirport());
                preparedStatement.setInt(2, employee.getIdAirline());
                preparedStatement.setInt(3, employee.getIdCountry());
                preparedStatement.setString(4, employee.getAdmissionDate());
                preparedStatement.setString(5, employee.getIdUser());
                int rowsAff = preparedStatement.executeUpdate();
                if(rowsAff==1){
                    return Optional.of(employee);
                }else{
                    return Optional.empty();
                }
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB error"+e.getMessage(),e);
            }
    }

    @Override
    public void deleteEmployee(String id) {
        String query = "DELETE FROM employee WHERE idUser = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff<1){
                throw new SQLException("Failed to delete! "); 
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error "+e.getMessage(),e);
        }
    }
    
}
