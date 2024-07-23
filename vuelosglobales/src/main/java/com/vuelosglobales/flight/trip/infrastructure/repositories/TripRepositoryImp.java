package com.vuelosglobales.flight.trip.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.protocol.Resultset;
import com.vuelosglobales.flight.trip.domain.models.Trip;
import com.vuelosglobales.flight.trip.domain.ports.out.TripRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class TripRepositoryImp implements TripRepository{
    private final DBConnection dbConnection;

    public TripRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Trip> findTripById(int id) {
        String query = "SELECT id,idRoute,idCrew,date,idStatus,idPlane FROM trip WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Trip trip = new Trip(
                            resultSet.getInt("id"),
                            resultSet.getInt("idRoute"),
                            resultSet.getInt("idCrew"),
                            resultSet.getString("date"),
                            resultSet.getInt("idStatus"),
                            resultSet.getString("idPlane")
                    );
                    return Optional.of(trip);
                }else{
                    return Optional.empty();
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
           throw new RuntimeException("DB error"+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Trip> updateCrewTrip(Trip trip) {
        String query = "UPDATE trip SET idCrew=? WHERE id=?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, trip.getIdCrew());
            preparedStatement.setInt(2, trip.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff<1){
                return Optional.empty();
            }else{
                return Optional.of(trip);
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deleteTrip(int id) {
        String query = "DELETE FROM trip WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff<1){
                return false;
            }else{
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public List<List<String>> ShowTrips() {
        String query = "SELECT T.id,C1.name AS CityDepature,C2.name AS CityArrival ,T.idCrew,T.date,S.name,T.idPlane FROM trip T INNER JOIN route R ON T.idRoute = R.id INNER JOIN status S ON T.idStatus = S.id INNER JOIN city C1 ON R.idDepature = C1.id INNER JOIN city C2 ON R.idArrival = C2.id";
        List<List<String>> tripList = new ArrayList<>();
        String idTrip = "";                
        String cDepature ="";
        String cArrival = "";
        String idCrew = "";
        String date = "";
        String status = "";
        String idPlane = "";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                List<String> tripSingular = new ArrayList<>();
                tripSingular.add(Integer.toString(resultSet.getInt("T.id")));//idx 0
                tripSingular.add(resultSet.getString("CityDepature"));//idx 1
                tripSingular.add(resultSet.getString("CityArrival"));//idx 2
                tripSingular.add(Integer.toString(resultSet.getInt("T.idCrew")));//idx 3
                tripSingular.add(resultSet.getString("T.date"));//idx 4
                tripSingular.add(resultSet.getString("S.name"));//idx 5
                tripSingular.add(resultSet.getString("T.idPlane"));//idx6
                tripList.add(tripSingular);
            }
            return tripList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB ERROR "+e.getMessage(),e);
        }
    }
    
}
