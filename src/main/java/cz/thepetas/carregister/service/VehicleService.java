package cz.thepetas.carregister.service;

import cz.thepetas.carregister.exception.CarWithIdMarkExists;
import cz.thepetas.carregister.exception.VehicleNotFound;
import cz.thepetas.carregister.model.Car;
import cz.thepetas.carregister.model.Vehicle;

import java.util.List;


public interface VehicleService {
    public Vehicle create(Vehicle vehicle) throws CarWithIdMarkExists;

    public Vehicle delete(long id) throws VehicleNotFound;

    public List<Vehicle> findAll();

    public Vehicle findById(long id);

    public List<Vehicle> findAllByType(String type);

    public Vehicle update(Vehicle vehicle) throws CarWithIdMarkExists, VehicleNotFound;
}
