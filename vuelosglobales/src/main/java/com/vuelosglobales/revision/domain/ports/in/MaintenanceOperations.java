package com.vuelosglobales.revision.domain.ports.in;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.revision.domain.models.Maintenance;

public interface MaintenanceOperations {
    Optional<Maintenance> addMaintenance(Maintenance maintenance);
    Optional<Maintenance> updateMaintenance(Maintenance maintenance);
    boolean deleteMaintenance(int id);
    List<String> listMaintenanceByPlaneId(String idPlane);
    Optional<Maintenance> maintenanceExists(int id);
}
