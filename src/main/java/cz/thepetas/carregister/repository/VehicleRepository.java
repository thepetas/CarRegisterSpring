package cz.thepetas.carregister.repository;

import cz.thepetas.carregister.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * from vehicles where type_vehicle = ?1",
            nativeQuery = true)
    public List<Vehicle> findyAllByType(String type);

    @Query(value = "SELECT * from vehicles where id_mark = ?1",
            nativeQuery = true)
    public Vehicle findyByIdMark(String mark);
}
