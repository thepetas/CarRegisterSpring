package cz.thepetas.carregister.web.controller;

import cz.thepetas.carregister.exception.CarWithIdMarkExists;
import cz.thepetas.carregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.carregister.data.model.Address;
import cz.thepetas.carregister.data.model.Car;
import cz.thepetas.carregister.data.model.Person;
import cz.thepetas.carregister.service.AddressService;
import cz.thepetas.carregister.service.PersonService;
import cz.thepetas.carregister.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {


    @Autowired
    private PersonService personService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


    @RequestMapping("/gen")
    public String generateData() throws CarWithIdMarkExists {

        Address address = new Address();
        address.setStreet("Jana s ");
        address.setHouseNumber("123/45");
        address.setZipCode("16556967");
        address.setCity("Tabor");

        address = addressService.create(address);


        Address address1 = new Address();
        address1.setStreet("Jaadfadna s ");
        address1.setHouseNumber("12dafdfa3/45");
        address1.setZipCode("165daff56967");
        address1.setCity("Taadfbor");

        address1 = addressService.create(address1);

        Address address2 = new Address();
        address2.setStreet("Ja1111na s ");
        address2.setHouseNumber("123/45");
        address2.setZipCode("165561adfaf223967");
        address2.setCity("Tabor");

        address2 = addressService.create(address2);

        Person person = new Person();
        person.setName("Petr");
        person.setSurname("Pansky");
        person.setBirthNumber("123456789");
        person.setAddress(address);

        try {
            person = personService.create(person);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }

        Person person1 = new Person();
        person1.setName("Honza");
        person1.setSurname("Novak");
        person1.setBirthNumber("129876789");
        person1.setAddress(address1);

        try {
            person1 = personService.create(person1);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }

        Person person2 = new Person();
        person2.setName("Kamil");
        person2.setSurname("Stehno");
        person2.setBirthNumber("123656784");
        person2.setAddress(address2);

        try {
            person2 = personService.create(person2);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }

        Car car1 = new Car();
        car1.setModel("MODEL1");
        car1.setBrand("BRAND1");
        car1.setIdMark("SPZ1");
        car1.setOwner(person);

        Car car2 = new Car();
        car2.setModel("MODEL1");
        car2.setBrand("BRAND1");
        car2.setIdMark("SPZ1");

        Car car3 = new Car();
        car3.setModel("MODEL1");
        car3.setBrand("BRAND1");
        car3.setIdMark("SPZ1");

        try {
            vehicleService.create(car1);
        } catch (CarWithIdMarkExists carWithIdMarkExists) {
            carWithIdMarkExists.printStackTrace();
        }
        try {
            vehicleService.create(car2);
        } catch (CarWithIdMarkExists carWithIdMarkExists) {
            carWithIdMarkExists.printStackTrace();
        }
        try {
            vehicleService.create(car3);
        } catch (CarWithIdMarkExists carWithIdMarkExists) {
            carWithIdMarkExists.printStackTrace();
        }


        return "redirect:/";
    }


}
