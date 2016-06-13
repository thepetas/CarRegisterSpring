package cz.thepetas.carregister.service.impl;

import cz.thepetas.carregister.exception.CarWithIdMarkExists;
import cz.thepetas.carregister.exception.VehicleNotFound;
import cz.thepetas.carregister.model.Car;
import cz.thepetas.carregister.model.Vehicle;
import cz.thepetas.carregister.repository.VehicleRepository;
import cz.thepetas.carregister.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) throws CarWithIdMarkExists {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            Car c = (Car) vehicleRepository.findyByIdMark(car.getIdMark());
            if (c != null) {
                throw new CarWithIdMarkExists();
            }
        }
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle delete(long id) throws VehicleNotFound {
        Vehicle vehicle = vehicleRepository.findOne(id);
        if (vehicle == null) {
            throw new VehicleNotFound();
        }

        vehicleRepository.delete(vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(long id) {
        return vehicleRepository.findOne(id);
    }

    @Override
    public List<Vehicle> findAllByType(String type) {
        return vehicleRepository.findyAllByType(type);
    }

    @Override
    public Vehicle update(Vehicle vehicle) throws CarWithIdMarkExists, VehicleNotFound {
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            Vehicle finded = vehicleRepository.findyByIdMark(car.getIdMark());
            if (finded != null && finded.getId() != car.getId()) {
                throw new CarWithIdMarkExists();
            }

            finded = vehicleRepository.findOne(car.getId());
            if (finded == null) {
                throw new VehicleNotFound();
            }

            Car carFinded = (Car) finded;
            carFinded.setIdMark(car.getIdMark());
            carFinded.setModel(car.getModel());
            carFinded.setBrand(car.getBrand());
            carFinded.setOwner(car.getOwner());
            return carFinded;
        }
        return null;
    }


}
