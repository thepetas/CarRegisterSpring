package cz.thepetas.carregister.service.impl;

import cz.thepetas.carregister.model.Address;
import cz.thepetas.carregister.model.Vehicle;
import cz.thepetas.carregister.repository.AddressRepository;
import cz.thepetas.carregister.repository.PersonRepository;
import cz.thepetas.carregister.exception.PersonNotFound;
import cz.thepetas.carregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.carregister.model.Person;
import cz.thepetas.carregister.service.AddressService;
import cz.thepetas.carregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonRepository personRepositoty;

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional
    public Person create(Person person) throws PersonWithBirthNumberExists {
        Person finded = personRepositoty.findByBirthNumber(person.getBirthNumber());
        if (finded != null) {
            throw new PersonWithBirthNumberExists();
        }

        Address address = addressService.create(person.getAddress());
        person.setAddress(address);
        return personRepositoty.save(person);
    }

    @Override
    @Transactional(rollbackFor = PersonNotFound.class)
    public Person delete(long id) throws PersonNotFound {
        Person finded = personRepositoty.findOne(id);
        if (finded == null) {
            throw new PersonNotFound();
        }
        for (Vehicle v : finded.getVehicles()) {
            v.setOwner(null);
        }
        personRepositoty.delete(finded);

        return finded;
    }

    @Override
    public List<Person> findAll() {
        return personRepositoty.findAll();
    }

    @Override
    @Transactional
    public Person update(Person person) throws PersonWithBirthNumberExists, PersonNotFound {

        Person finded = personRepositoty.findByBirthNumber(person.getBirthNumber());
        if (finded != null && person.getId() != finded.getId()) {
            throw new PersonWithBirthNumberExists();
        }

        finded = personRepositoty.findOne(person.getId());
        if (finded == null)
            throw new PersonNotFound();

        finded.setName(person.getName());
        finded.setSurname(person.getSurname());
        finded.setBirthNumber(person.getBirthNumber());
        Address address = addressService.create(person.getAddress());
        finded.setAddress(address);
        return finded;
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
