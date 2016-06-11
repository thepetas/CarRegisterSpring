package cz.thepetas.carregister.service;

import cz.thepetas.carregister.exception.PersonNotFound;
import cz.thepetas.carregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.carregister.model.Person;

import java.util.List;

public interface PersonService {

    public Person create(Person person) throws PersonWithBirthNumberExists;

    public Person delete(long id) throws PersonNotFound;

    public List<Person> findAll();

    public Person update(Person person) throws PersonNotFound, PersonWithBirthNumberExists;

    public Person findById(long id);

    public Person findByBirthNumber(String birthNumber);
}
