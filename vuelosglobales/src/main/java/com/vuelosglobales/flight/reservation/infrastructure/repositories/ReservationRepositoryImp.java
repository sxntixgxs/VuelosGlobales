package com.vuelosglobales.flight.reservation.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.protobuf.Option;
import com.vuelosglobales.flight.reservation.domain.models.Reservation;
import com.vuelosglobales.flight.reservation.domain.ports.out.ReservationRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class ReservationRepositoryImp implements ReservationRepository{
    private final DBConnection dbConnection;

    public ReservationRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Reservation> addReservation(Reservation reservation) {
        String query = "INSERT INTO flightReservation(idCustomer,idTrip,idFlightFare)VALUES(?,?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, reservation.getIdCustomer());
            preparedStatement.setInt(2, reservation.getIdFlightFare());
            preparedStatement.setInt(3, reservation.getIdFlightFare());
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return Optional.of(reservation);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Fail trying to add reservation "+e.getMessage(),e);
        }
    }

    @Override
    public List<List<String>> findReservationByCustomerId(String idCustomer) {
        String query = "SELECT R.id,R.idCustomer,T.date,FF.name\n" + //
                        "FROM flightReservation R\n" + //
                        "INNER JOIN trip T ON R.idTrip = T.id\n" + //
                        "INNER JOIN flightFare FF ON R.idFlightFare = FF.id\n" + //
                        "WHERE idCustomer = ?;";
        List<List<String>> reservationList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            System.out.println(idCustomer);
            preparedStatement.setString(1, idCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                List<String> reservationInfo = new ArrayList<>();
                reservationInfo.add("Reservation ID: "+resultSet.getInt("R.id"));
                reservationInfo.add("Customer ID: "+resultSet.getString("R.idCustomer"));
                reservationInfo.add("Date :"+resultSet.getString("T.date"));
                reservationInfo.add("Flight fare: "+resultSet.getString("FF.name"));
                reservationList.add(reservationInfo);
            }
            return reservationList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to recovery reservation with customer id "+e.getMessage(),e);
        }
    }

    @Override
    public List<List<String>> findReservationByTripId(int idTrip) {
        String query = "SELECT R.id,R.idCustomer,T.date,FF.name\n" + //
                        "FROM flightReservation R\n" + //
                        "INNER JOIN trip T ON R.idTrip = T.id\n" + //
                        "INNER JOIN flightFare FF ON R.idFlightFare = FF.id\n" + //
                        "WHERE idTrip = ?;";
        List<List<String>> reservationList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idTrip);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                List<String> reservationInfo = new ArrayList<>();
                reservationInfo.add("Reservation ID: "+resultSet.getInt("R.id"));
                reservationInfo.add("Customer ID: "+resultSet.getString("R.idCustomer"));
                reservationInfo.add("Date :"+resultSet.getString("T.date"));
                reservationInfo.add("Flight fare: "+resultSet.getString("FF.name"));
                reservationList.add(reservationInfo);
            }
            return reservationList;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to recovery reservation with trip id "+e.getMessage(),e);
        }
    }

    @Override
    public boolean deleteReservation(int idReservation) {
        String query = "DELETE FROM flightReservation WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idReservation);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failied to delete reservation "+e.getMessage(),e);
        }
    }

    @Override
    public List<String> getReservationById(int idReservation) {
        String query = "SELECT id,idCustomer,idTrip,idFlightFare FROM flightReservation WHERE id = ?";
        List<String> reservationData = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idReservation);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                reservationData.add("RESERVATION ID: "+resultSet.getInt("id"));
                reservationData.add("CUSTOMER ID: "+resultSet.getString("idCustomer"));
                reservationData.add("TRIP ID: "+resultSet.getInt("idTrip"));
                reservationData.add("FLIGHT FARE ID: "+resultSet.getInt("idFlightFare"));
            }
            return reservationData;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failied to get reservation by id"+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Reservation> getReservationByComponents(Reservation reservation) {
        String query = "SELECT id,idCustomer,idTrip,idFlightFare FROM flightReservation WHERE idCustomer = ? AND idTrip = ? AND idFlightFare = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, reservation.getIdCustomer());
            preparedStatement.setInt(2, reservation.getIdTrip());
            preparedStatement.setInt(3, reservation.getIdFlightFare());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Reservation resultReservation = new Reservation(
                    resultSet.getInt("id"),
                    resultSet.getString("idCustomer"),
                    resultSet.getInt("idTrip"),
                    resultSet.getInt("idFlightFare")
                );
                return Optional.of(resultReservation);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to get reservation by components "+e.getMessage(),e);
        }
    }
}
