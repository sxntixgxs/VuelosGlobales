package com.vuelosglobales.revision.domain.ports.out;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.revision.domain.models.Maintenance;

public interface MaintenanceRepository {
    Optional<Maintenance> saveMaintenance(Maintenance maintenance);
    Optional<Maintenance> updateMaintenance(Maintenance maintenance);
    boolean deleteMaintenance(int id);
    List<String> listMaintenanceByPlaneId(String idPlane);
    Optional<Maintenance> maintenanceExists(int id);
}
