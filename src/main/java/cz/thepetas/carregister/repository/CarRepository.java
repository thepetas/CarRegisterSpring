package cz.thepetas.carregister.repository;

import cz.thepetas.carregister.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
