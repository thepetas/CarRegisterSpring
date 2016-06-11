package cz.thepetas.musicregister.controller;


import cz.thepetas.musicregister.exception.PersonNotFound;
import cz.thepetas.musicregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.musicregister.model.Address;
import cz.thepetas.musicregister.model.Person;
import cz.thepetas.musicregister.service.AddressService;
import cz.thepetas.musicregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonService personService;

    @Autowired
    AddressService addressService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/persons").setViewName("/persons");
    }


    @RequestMapping(value = "/persons/new", method = RequestMethod.GET)
    public String savePage(Person person) {
        return "person/new";
    }

    @RequestMapping(value = {"/persons/new"}, method = RequestMethod.POST)
    public String savePage(@Valid Person person,
                           BindingResult bindingResult,
                           final RedirectAttributes redirectAttributes) {

        if (bindingResult.getErrorCount() == 1) {
            Address address = new Address();
            address.setStreet("a");
            address.setHouseNumber("a");
            address.setZipCode("a");
            address.setCity("a");
            address = addressService.create(address);
            person.setAddress(address);

        }

        if (bindingResult.hasErrors() && bindingResult.getErrorCount() != 1) {
            return "person/new";
        }

        try {
            long id = personService.create(person).getId();
            redirectAttributes.addFlashAttribute("message", "Person '" + id + "' was succesfully added");
            return "redirect:/persons";
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            redirectAttributes.addFlashAttribute("message", "Person with birthNumber '" + person.getBirthNumber() + "' already exists");
            return "redirect:/persons/new";
        }

    }

    @RequestMapping("/persons")
    public String index(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person/index";
    }

    @RequestMapping(value = "/persons/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long personId, final RedirectAttributes redirectAttributes) {
        try {
            personService.delete(personId);
            redirectAttributes.addFlashAttribute("message", "Person '" + personId + "' was deleted.");

        } catch (PersonNotFound personNotFound) {
            redirectAttributes.addFlashAttribute("message", "Person '" + personId + "' doesn't exist.");
        }

        return "redirect:/persons";
    }

}
