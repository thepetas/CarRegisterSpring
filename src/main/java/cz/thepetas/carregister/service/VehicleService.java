package cz.thepetas.carregister.service;

import cz.thepetas.carregister.exception.AddressNotFound;
import cz.thepetas.carregister.exception.VehicleNotFound;
import cz.thepetas.carregister.model.Address;
import cz.thepetas.carregister.model.Vehicle;

import java.util.List;

public interface VehicleService {
    public Vehicle create(Vehicle vehicle);

    public Vehicle delete(long id) throws VehicleNotFound;

    public List<Vehicle> findAll();

    public Vehicle findById(long id);

    public Address findByType(Vehicle vehicle);
}
