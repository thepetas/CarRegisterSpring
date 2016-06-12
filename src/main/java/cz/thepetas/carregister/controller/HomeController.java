package cz.thepetas.carregister.controller;

import cz.thepetas.carregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.carregister.model.Address;
import cz.thepetas.carregister.model.Person;
import cz.thepetas.carregister.service.AddressService;
import cz.thepetas.carregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {


    @Autowired
    private PersonService personService;

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
    public String generateData() {

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

        address = addressService.create(address2);

        Person person = new Person();
        person.setName("Petr");
        person.setSurname("Pansky");
        person.setBirthNumber("123456789");
        person.setAddress(address);

        try {
            personService.create(person);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }

        Person person1 = new Person();
        person1.setName("Honza");
        person1.setSurname("Novak");
        person1.setBirthNumber("129876789");
        person1.setAddress(address1);

        try {
            personService.create(person1);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }

        Person person2 = new Person();
        person2.setName("Kamil");
        person2.setSurname("Stehno");
        person2.setBirthNumber("123656784");
        person2.setAddress(address2);

        try {
            personService.create(person2);
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personWithBirthNumberExists.printStackTrace();
        }


        return "redirect:/";
    }


}
