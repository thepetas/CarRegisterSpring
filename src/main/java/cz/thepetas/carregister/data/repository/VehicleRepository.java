package cz.thepetas.carregister.data.repository;

import cz.thepetas.carregister.data.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * from vehicles where type_vehicle = ?1",
            nativeQuery = true)
    public List<Vehicle> findyAllByType(String type);

    @Query(value = "SELECT * from vehicles where id_mark = ?1",
            nativeQuery = true)
    public Vehicle findyByIdMark(String mark);
}
