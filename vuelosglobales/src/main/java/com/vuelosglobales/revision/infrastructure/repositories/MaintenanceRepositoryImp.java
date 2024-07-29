package com.vuelosglobales.revision.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vuelosglobales.revision.domain.models.Maintenance;
import com.vuelosglobales.revision.domain.ports.out.MaintenanceRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class MaintenanceRepositoryImp implements MaintenanceRepository{
    private final DBConnection dbConnection;

    public MaintenanceRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Maintenance> saveMaintenance(Maintenance maintenance) {
        String query = "INSERT INTO maintenancePlane(date,desc,idEmployee,idPlane)VALUES(?,?,?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setDate(1, maintenance.getDate());
            preparedStatement.setString(2,maintenance.getDescription());
            preparedStatement.setString(3, maintenance.getIdEmployee());
            preparedStatement.setString(4, maintenance.getIdPlane());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(maintenance);
            }else{
                return Optional.empty();
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB failed trying to add Maintenance "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Maintenance> updateMaintenance(Maintenance maintenance) {
        String query = "UPDATE maintenancePlane SET date = ?, desc = ?, idEmployee = ?, idPlane = ? WHERE id=?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setDate(1, maintenance.getDate());
            preparedStatement.setString(2, maintenance.getDescription());
            preparedStatement.setString(3, maintenance.getIdEmployee());
            preparedStatement.setString(4, maintenance.getIdPlane());
            preparedStatement.setInt(5, maintenance.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(maintenance);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB failed trying to update maintenance "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deleteMaintenance(int id) {
        String query = "DELETE FROM maintenancePlane WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement  = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB failed trying to delete maintenance "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> listMaintenanceByPlaneId(String idPlane) {
        String query = "SELECT id, idEmployee, idPlane, date, desc FROM maintenancePlane WHERE idPlane = ?";
        List<String> listMaintenance = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idPlane);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listMaintenance.add("MAINTENANCE ID: "+resultSet.getInt("id")+" PLANE ID: "+resultSet.getString("idPlane")+" TECHNICIAN ID: "+resultSet.getString("idEmployee")+" DATE "+resultSet.getDate("date")+" DESCRIPTION: "+resultSet.getString("desc"));
            }
            return listMaintenance;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB failed trying to list maintenance of a plane "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Maintenance> maintenanceExists(int id) {
        String query = "SELECT id,idEmployee,idPlane,date,desc FROM maintenancePlane WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Maintenance maintenance = new Maintenance(resultSet.getInt(id), resultSet.getString("idEmployee"), resultSet.getString("idPlane"), resultSet.getDate("date"), resultSet.getString("desc"));
                return Optional.of(maintenance);  
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB failed trying to find maintenance "+e.getMessage(),e);
        }
    }
}
