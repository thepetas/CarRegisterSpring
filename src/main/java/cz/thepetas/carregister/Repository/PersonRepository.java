package cz.thepetas.carregister.Repository;

import cz.thepetas.carregister.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by petr on 6/11/16.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * from persons where birth_number = ?1",
            nativeQuery = true)
    public Person findByBirthNumber(String birthNumber);
}
