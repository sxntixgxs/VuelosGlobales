package com.vuelosglobales.plane.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.domain.ports.out.PlaneRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class PlaneRepositoryImp implements PlaneRepository{
    private final DBConnection dbConnection;

    public PlaneRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Plane savePlane(Plane plane) {
        String query = "INSERT INTO plane(id,capacity,fabrication,idStatus,idAirline,idModel) VALUES(?,?,?,?,?,?)";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, plane.getId());
                preparedStatement.setInt(2, plane.getCapacity());
                preparedStatement.setString(3, plane.getFabrication());
                preparedStatement.setInt(4, plane.getIdStatus());
                preparedStatement.setInt(5, plane.getIdAirline());
                preparedStatement.setInt(6, plane.getIdModel());
                int rowsAff = preparedStatement.executeUpdate();
                if(rowsAff==1){
                    return plane;
                }else{
                    throw new SQLException("Failed to insert");
                }
            }catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("DB ERROR "+e.getMessage(),e);
            }
    }

    @Override
    public Optional<Plane> findPlaneById(String id) {
        String query = "SELECT id,capacity,fabrication,idStatus,idAirline,idModel FROM plane WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        Plane plane = new Plane(resultSet.getString("id"),resultSet.getInt("capacity"),resultSet.getString("fabrication"),resultSet.getInt("idStatus"),resultSet.getInt("idAirline"), resultSet.getInt("idModel"));
                        return Optional.of(plane);
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
    public void deletePlanebyId(String id) {
        String query = "DELETE FROM plane WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff<1){
                throw new SQLException("Failed to delete! ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public Plane updatePlane(Plane plane) {
        String query = "UPDATE plane SET capacity=?,fabrication=?,idStatus=?,idAirline=?,idModel=? WHERE id = ?";
        try(Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, plane.getCapacity());
            preparedStatement.setString(2, plane.getFabrication());
            preparedStatement.setInt(3, plane.getIdStatus());
            preparedStatement.setInt(4, plane.getIdAirline());
            preparedStatement.setInt(5, plane.getIdModel());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return plane;
            }else{
                throw new SQLException("Failed to update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB error"+e.getMessage(),e);
        }
    }
    
}
