package com.vuelosglobales.flight.employee.domain.models;

public class Crew {
    private String idPilot;
    private String idCopilot;
    private String crewLeader;
    private String crewAssistant;
    private String crewAssistant2;
    public Crew() {
    }
    public Crew(String idPilot, String idCopilot, String crewLeader, String crewAssistant, String crewAssistant2) {
        this.idPilot = idPilot;
        this.idCopilot = idCopilot;
        this.crewLeader = crewLeader;
        this.crewAssistant = crewAssistant;
        this.crewAssistant2 = crewAssistant2;
    }
    public String getIdPilot() {
        return idPilot;
    }
    public void setIdPilot(String idPilot) {
        this.idPilot = idPilot;
    }
    public String getIdCopilot() {
        return idCopilot;
    }
    public void setIdCopilot(String idCopilot) {
        this.idCopilot = idCopilot;
    }
    public String getCrewLeader() {
        return crewLeader;
    }
    public void setCrewLeader(String crewLeader) {
        this.crewLeader = crewLeader;
    }
    public String getCrewAssistant() {
        return crewAssistant;
    }
    public void setCrewAssistant(String crewAssistant) {
        this.crewAssistant = crewAssistant;
    }
    public String getCrewAssistant2() {
        return crewAssistant2;
    }
    public void setCrewAssistant2(String crewAssistant2) {
        this.crewAssistant2 = crewAssistant2;
    }
}
