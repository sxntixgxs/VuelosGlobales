package com.vuelosglobales.flight.employee.domain.ports.in;

import java.util.Optional;

import com.vuelosglobales.flight.employee.domain.models.Crew;

public interface CrewOperations {
    Optional<Integer> createCrew(String idPilot, String idCopilot, String crewLeader, String crewAssistant, String crewAssistant2);
    boolean assignCrewToTrip(Crew crew, int idtTrip);
    Optional<Crew> findCrewById(int idCrew);
    void showCrew(int idCrew);
}
