package com.vuelosglobales.plane.infrastructure.repositories;

import java.util.Optional;

import com.vuelosglobales.plane.domain.models.Plane;
import com.vuelosglobales.plane.domain.ports.out.PlaneRepository;
import com.vuelosglobales.user.infrastructure.config.DBConnection;

public class PlaneRepositoryImp implements PlaneRepository{
    private final DBConnection dbConnection;

    public PlaneRepositoryImp(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Plane savePlane(Plane plane) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePlane'");
    }

    @Override
    public Optional<Plane> findPlaneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPlaneById'");
    }

    @Override
    public void deletePlanebyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePlanebyId'");
    }

    @Override
    public Plane updatePlane(Plane plane) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlane'");
    }
    
}
