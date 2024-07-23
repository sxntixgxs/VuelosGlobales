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
    public List<Reservation> findReservationByCustomerId(String idCustomer) {
        String query = "SELECT id,idCustomer,idTrip,idFlightFare FROM flightReservation WHERE idCustomer = ?";
        List<Reservation> reservationList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reservation reservation = new Reservation(
                    resultSet.getInt("id"),
                    resultSet.getString("idCustomer"),
                    resultSet.getInt("idTrip"),
                    resultSet.getInt("idFlightFare")
                );
                reservationList.add(reservation);
            }
            return reservationList;
            // if(resultSet.next()){
            //     Reservation reservation = new Reservation(
            //         resultSet.getInt("id"),
            //         resultSet.getString("idCustomer"),
            //         resultSet.getInt("idTrip"),
            //         resultSet.getInt("idFlightFare")
            //     );
            //     return Optional.of(reservation);
            // }else{
            //     return Optional.empty();
            // }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to recovery reservation with customer id "+e.getMessage(),e);
        }
    }

    @Override
    public List<Reservation> findReservationByTripId(String idTrip) {
        String query = "SELECT id,idCustomer,idTrip,idFlightFare FROM flightReservation WHERE idTrip = ?";
        List<Reservation> reservationList = new ArrayList<>();
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idTrip);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reservation reservation = new Reservation(
                    resultSet.getInt("id"),
                    resultSet.getString("idCustomer"),
                    resultSet.getInt("idTrip"),
                    resultSet.getInt("idFlightFare")
                );
                reservationList.add(reservation);
            }
            return reservationList;
            // if(resultSet.next()){
            //     Reservation reservation = new Reservation(
            //         resultSet.getInt("id"),
            //         resultSet.getString("idCustomer"),
            //         resultSet.getInt("idTrip"),
            //         resultSet.getInt("idFlightFare")
            //     );
            //     return Optional.of(reservation);
            // }else{
            //     return Optional.empty();
            // }
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
    public Optional<Reservation> getReservationById(int idReservation) {
        String query = "SELECT id,idCustomer,idTrip,idFlightFare FROM flightReservation WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idReservation);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Reservation reservation = new Reservation(
                    resultSet.getInt("id"),
                    resultSet.getString("idCustomer"),
                    resultSet.getInt("idTrip"),
                    resultSet.getInt("idFlightFare")
                );
                return Optional.of(reservation);
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failied to get reservation by id"+e.getMessage(),e);
        }
    }
}
