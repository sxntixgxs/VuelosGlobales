package com.vuelosglobales.flight.employee.domain.ports.out;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Crew;

public interface CrewRepository {
    Optional<Integer> saveCrew(String idPilot, String idCopilot, String idCrewLeader,String idCrewAssistant, String idCrewAssistant2);
    boolean setCrewOnTrip(int idCrew, int idTrip);
    Optional<Crew> findCrewById(int idCrew);
    Optional<Integer> findIdCrewByComponents(String idPilot,String idCopilot, String idCrewLeader, String idCrewAssistant, String idCrewAssistant2);
}
