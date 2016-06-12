package cz.thepetas.carregister.repository;

import cz.thepetas.carregister.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * from addresses where street = ?1 and " +
            "house_number = ?2 and " +
            "zip_code = ?3 and " +
            "city = ?4 ", nativeQuery = true)
    Address findByDetails(String street, String houseNumber, String zipCode, String city);
}
