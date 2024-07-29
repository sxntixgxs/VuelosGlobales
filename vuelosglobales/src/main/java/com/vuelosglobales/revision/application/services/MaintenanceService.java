package com.vuelosglobales.revision.application.services;

import java.util.List;
import java.util.Optional;

import com.vuelosglobales.revision.domain.models.Maintenance;
import com.vuelosglobales.revision.domain.ports.in.MaintenanceOperations;
import com.vuelosglobales.revision.domain.ports.out.MaintenanceRepository;

public class MaintenanceService implements MaintenanceOperations{
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Optional<Maintenance> addMaintenance(Maintenance maintenance) {
        return maintenanceRepository.saveMaintenance(maintenance);
    }

    @Override
    public Optional<Maintenance> updateMaintenance(Maintenance maintenance) {
        return maintenanceRepository.updateMaintenance(maintenance);
    }

    @Override
    public boolean deleteMaintenance(int id) {
        return maintenanceRepository.deleteMaintenance(id);
    }

    @Override
    public List<String> listMaintenanceByPlaneId(String idPlane) {
        return maintenanceRepository.listMaintenanceByPlaneId(idPlane);
    }

    @Override
    public Optional<Maintenance> maintenanceExists(int id) {
        return maintenanceRepository.maintenanceExists(id);
    }
}
