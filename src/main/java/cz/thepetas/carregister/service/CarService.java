package cz.thepetas.carregister.service;

import cz.thepetas.carregister.exception.CarNotFound;
import cz.thepetas.carregister.exception.VehicleNotFound;
import cz.thepetas.carregister.model.Address;
import cz.thepetas.carregister.model.Car;
import cz.thepetas.carregister.model.Vehicle;

import java.util.List;

public interface CarService {
    public Car create(Car car);

    public Car delete(long id) throws CarNotFound;

    public List<Car> findAll();

    public Car findById(long id);

    public Car findByIdMark(String idMark);
}
