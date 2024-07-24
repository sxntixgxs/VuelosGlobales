package com.vuelosglobales.flight.scale.infrastructure.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.event.ListDataListener;

import com.mysql.cj.jdbc.CallableStatement;
import com.vuelosglobales.flight.scale.domain.models.Scale;
import com.vuelosglobales.flight.scale.domain.ports.out.ScaleRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class ScaleRepositoryImp implements ScaleRepository{
    private final DBConnection dbConnection;

    public ScaleRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<List<String>> getScalesOfTrip(int idTrip) {
        String query = "SELECT S.id, C.name, S.date, S.idTrip FROM flightScales S INNER JOIN city C ON S.idScaleCity = C.id WHERE S.idTrip = ?";
        List<List<String>> scaleWrap = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idTrip);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                List<String> scaleList = new ArrayList<>();
                scaleList.add("Scale ID: "+String.valueOf(resultSet.getInt("S.id")));
                scaleList.add("Scale on "+resultSet.getString("C.name"));
                scaleList.add("Date: "+resultSet.getString("S.date"));
                scaleList.add("Trip ID: "+String.valueOf(resultSet.getInt("S.idTrip")));
                scaleWrap.add(scaleList);
            }return scaleWrap;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get scales with trip id "+e.getMessage(),e);
        }
    }

    @Override
    public List<List<String>> getScalesByComponents(int idScaleCity, int idTrip, Date date) {
        String query = "SELECT S.id, C.name, S.date, S.idTrip FROM flightScales S INNER JOIN city C ON S.idScaleCity = C.id WHERE S.idTrip = ? AND S.idScaleCity = ? AND S.date = ?";
        List<List<String>> scaleWrap = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idTrip);
            preparedStatement.setInt(2, idScaleCity);
            preparedStatement.setDate(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                List<String> scaleList = new ArrayList<>();
                scaleList.add("Scale ID: "+String.valueOf(resultSet.getInt("S.id")));
                scaleList.add("Scale on "+resultSet.getString("C.name"));
                scaleList.add("Date: "+resultSet.getString("S.date"));
                scaleList.add("Trip ID: "+String.valueOf(resultSet.getInt("S.idTrip")));
                scaleWrap.add(scaleList);
            }return scaleWrap;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get scales with trip components "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Scale> updateScale(Scale scale) {
        String query = "CALL updateScaleCity(?, ?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(2, scale.getIdTrip());
            preparedStatement.setInt(1, scale.getId());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff>=1){
                return Optional.of(scale);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed tryng to update scale "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deleteScale(int idScale) {
        String query = "DELETE FROM flightScales WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idScale);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed tryng to delete scale "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getCityListId() {
        String query = "SELECT id,name FROM city";
        List<String> cityList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(resultSet.getString("name")+" ID: "+resultSet.getInt("id"));
            }return cityList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get city "+e.getMessage(),e);
        }
    }

    @Override
    public List<List<String>> getTripIdList() {
        String query = "SELECT T.id, C1.name AS depatureCity, C2.name AS arrivalCity, T.date " +
                       "FROM trip T " +
                       "INNER JOIN route R ON T.idRoute = R.id " +
                       "INNER JOIN city C1 ON R.idDepature = C1.id " +
                       "INNER JOIN city C2 ON R.idArrival = C2.id";
        List<List<String>> wrap = new ArrayList<>();
        try (
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<String> tripIdList = new ArrayList<>();
                tripIdList.add("Trip id: " + resultSet.getInt("id"));
                tripIdList.add("Depature City: " + resultSet.getString("depatureCity"));
                tripIdList.add("Arrival City: " + resultSet.getString("arrivalCity"));
                tripIdList.add("Date: " + resultSet.getDate("date").toString());
                wrap.add(tripIdList);
            }
            return wrap;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed trying to get trip ids " + e.getMessage(), e);
        }
    }

    @Override
    public List<Integer> getScales() {
        String query = "SELECT id FROM flightScales";
        List<Integer> ids = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
            return ids;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to recovery scales "+e.getMessage(),e);
        }
    }
    
    
}
