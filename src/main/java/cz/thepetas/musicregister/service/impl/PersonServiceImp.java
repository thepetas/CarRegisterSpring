package cz.thepetas.musicregister.service.impl;

import cz.thepetas.musicregister.Repository.PersonRepository;
import cz.thepetas.musicregister.exception.PersonNotFound;
import cz.thepetas.musicregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.musicregister.model.Person;
import cz.thepetas.musicregister.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    @Resource
    private PersonRepository personRepositoty;

    @Override
    @Transactional
    public Person create(Person person) throws PersonWithBirthNumberExists {
        Person finded = personRepositoty.findByBirthNumber(person.getBirthNumber());
        if (finded != null) {
            throw new PersonWithBirthNumberExists();
        }
        return personRepositoty.save(person);
    }

    @Override
    @Transactional(rollbackFor = PersonNotFound.class)
    public Person delete(long id) throws PersonNotFound {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return personRepositoty.findAll();
    }

    @Override
    @Transactional
    public Person update(Person person) throws PersonWithBirthNumberExists, PersonNotFound {
        return null;
    }

    @Override
    public Person findById(long id) {
        return personRepositoty.findOne(id);
    }

    @Override
    public Person findByBirthNumber(String birthNumber) {
        return personRepositoty.findByBirthNumber(birthNumber);
    }
}
