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
        String query = "SELECT T.id,C1.name AS CityDepature,C2.name AS CityArrival ,T.idCrew,T.date,S.status,T.idPlane FROM trip T INNER JOIN route R ON T.idRoute = R.id INNER JOIN tripStatus S ON T.idStatus = S.id INNER JOIN city C1 ON R.idDepature = C1.id INNER JOIN city C2 ON R.idArrival = C2.id";
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
                tripSingular.add("Trip ID -> "+Integer.toString(resultSet.getInt("T.id")));//idx 0
                tripSingular.add("Depature -> "+resultSet.getString("CityDepature"));//idx 1
                tripSingular.add("Arrival -> "+resultSet.getString("CityArrival"));//idx 2
                tripSingular.add("Crew -> "+Integer.toString(resultSet.getInt("T.idCrew")));//idx 3
                tripSingular.add("Date -> "+resultSet.getString("T.date"));//idx 4
                tripSingular.add("Status ->"+resultSet.getString("S.status"));//idx 5
                tripSingular.add("Plane ID -> "+resultSet.getString("T.idPlane"));//idx6
                tripList.add(tripSingular);
            }
            return tripList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB ERROR "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Trip> updateTrip(Trip trip) {
        String query = "UPDATE trip SET idRoute=?,date=?,idStatus=?,idPlane=? WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, trip.getIdRoute());
            preparedStatement.setString(2, trip.getDate());
            preparedStatement.setInt(3, trip.getIdStatus());
            preparedStatement.setString(4, trip.getIdAirplane());
            preparedStatement.setInt(5,trip.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(trip);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to update trip "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAllCitiesWithId() {
        String query = "SELECT id,name FROM city";
        List<String> cityList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                cityList.add("ID: "+resultSet.getInt("id")+" // Name: "+resultSet.getString("name"));
            }return cityList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get cities "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<String> findCityById(int id) {
        String query = "SELECT name FROM city WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(resultSet.getString("name"));
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to find city with id: "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAllRoutesWithId() {
        String query = "SELECT R.id, C1.name,C2.name FROM route R INNER JOIN city C1 ON R.idDepature = C1.id INNER JOIN city C2 ON R.id = C2.id";
        List<String> cityList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                cityList.add(
                    "ROUTE ID: "+resultSet.getInt("R.id")
                );
                cityList.add(
                    "Depature: "+
                    resultSet.getString("C1.name")
                );
                cityList.add(
                    "Arrival: "+
                    resultSet.getString("C2.name")
                );
            }return cityList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get cities "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<String> findRouteById(int id) {
        String query = "SELECT R.id, C1.name,C2.name FROM route R INNER JOIN city C1 ON R.idDepature = C1.id INNER JOIN city C2 ON R.id = C2.id WHERE R.id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of("ROUTE ID: "+resultSet.getString("R.id")+" DEPATURE "+resultSet.getString("C1.name")+" ARRIVAL "+resultSet.getString("C2.name"));
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to recover route with id "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getAllStatusWithId() {
        String query = "SELECT id,status FROM tripStatus";
        List<String> statusList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                statusList.add("STATUS ID: "+resultSet.getInt("id")+" "+resultSet.getString("status"));
            }
            return statusList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to recover statuses: "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<String> findStatusById(int id) {
        String query = "SELECT name FROM status WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(resultSet.getString("name"));
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to recover status by id: "+e.getMessage(),e);
        }
    }
    
}
