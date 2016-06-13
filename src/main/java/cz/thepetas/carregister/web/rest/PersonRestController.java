package cz.thepetas.carregister.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import cz.thepetas.carregister.data.json.View;
import cz.thepetas.carregister.data.model.Person;
import cz.thepetas.carregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/persons")
@RestController
public class PersonRestController {

    @Autowired
    PersonService personService;

    @JsonView(View.SummaryFromPerson.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Person> getAll() {
        return personService.findAll();
    }

    @JsonView(View.SummaryFromPerson.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getOne(@PathVariable("id") Long personId) {
        return personService.findById(personId);
    }

}
