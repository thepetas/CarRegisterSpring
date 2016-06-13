package cz.thepetas.carregister.data.repository;

import cz.thepetas.carregister.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * from persons where birth_number = ?1",
            nativeQuery = true)
    public Person findByBirthNumber(String birthNumber);
}
