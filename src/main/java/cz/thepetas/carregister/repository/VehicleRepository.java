package cz.thepetas.carregister.repository;

import cz.thepetas.carregister.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
