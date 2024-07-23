package com.vuelosglobales.flight.employee.infrastructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.google.protobuf.Option;
import com.vuelosglobales.flight.employee.domain.models.Crew;
import com.vuelosglobales.flight.employee.domain.ports.out.CrewRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class CrewRepositoryImp implements CrewRepository{
    private final DBConnection dbConnection;

    public CrewRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Integer> saveCrew(String idPilot, String idCopilot, String idCrewLeader,String idCrewAssistant, String idCrewAssistant2) {
        String query = "INSERT INTO crew(idPilot,idCopilot,idCrewLeader,idCrewAssistant,idCrewAssistant2)VALUES(?,?,?,?,?)";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idPilot);
            preparedStatement.setString(2, idCopilot);
            preparedStatement.setString(3, idCrewLeader);
            preparedStatement.setString(4, idCrewAssistant);
            preparedStatement.setString(5, idCrewAssistant2);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff>=1){
                Crew crew = new Crew(idPilot,idCopilot,idCrewLeader,idCrewAssistant,idCrewAssistant2);
                return findIdCrewByComponents(idPilot, idCopilot, idCrewLeader, idCrewAssistant, idCrewAssistant2);
                
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }

    @Override
    public boolean setCrewOnTrip(int idCrew, int idTrip) {
        String query = "UPDATE trip SET idCrew = ? WHERE id = ?";//previamente debe existir el Crew en la DB.
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idCrew);
            preparedStatement.setInt(2, idTrip);
            int rowsAff = preparedStatement.executeUpdate();
            if(rowsAff==1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB error "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Crew> findCrewById(int idCrew) {
        String query = "SELECT idPilot,idCopilot,idCrewLeader,idCrewAssistant,idCrewAssistant2 FROM crew WHERE id = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, idCrew);
            try(
                ResultSet resultSet = preparedStatement.executeQuery()
            ){
                if(resultSet.next()){
                    Crew crew = new Crew(
                        resultSet.getString("idPilot"),
                        resultSet.getString("idCopilot"),
                        resultSet.getString("idCrewLeader"),
                        resultSet.getString("idCrewAssistant"),
                        resultSet.getString("idCrewAssistant2")
                    );
                    return Optional.of(crew);
                }else{
                    return Optional.empty();
                }
            } 
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB error "+e.getMessage(),e);
        }
    }

    @Override
    public Optional<Integer> findIdCrewByComponents(String idPilot, String idCopilot, String idCrewLeader, String idCrewAssistant, String idCrewAssistant2) {
        String query = "SELECT id FROM crew WHERE idPilot = ? AND idCopilot = ? AND idCrewLeader = ? AND idCrewAssistant = ? AND idCrewAssistant2 = ?";
        try(
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, idPilot);
            preparedStatement.setString(2, idCopilot);
            preparedStatement.setString(3, idCrewLeader);
            preparedStatement.setString(4, idCrewAssistant);
            preparedStatement.setString(5, idCrewAssistant2);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(resultSet.getInt("id"));
            }else{
                return Optional.empty();
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("DB Error"+e.getMessage(),e);
        }
    }
    
}
