package cz.thepetas.carregister.web.controller;


import cz.thepetas.carregister.exception.PersonNotFound;
import cz.thepetas.carregister.exception.PersonWithBirthNumberExists;
import cz.thepetas.carregister.data.model.Address;
import cz.thepetas.carregister.data.model.Person;
import cz.thepetas.carregister.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PersonController extends WebMvcConfigurerAdapter {

    @Autowired
    private PersonService personService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/persons").setViewName("/persons");
    }


    @RequestMapping(value = "/persons/new", method = RequestMethod.GET)
    public String newPerson(Person person, Address address) {
        return "person/new";
    }

    @RequestMapping(value = {"/persons/new"}, method = RequestMethod.POST)
    public String newPerson(@Valid Person person,
                            BindingResult personBindingResult,
                            @Valid Address address,
                            BindingResult addressBindingResult,
                            final RedirectAttributes redirectAttributes) {


        if (personBindingResult.getErrorCount() != 1 || addressBindingResult.hasErrors()) {
            return "person/new";
        }
        try {
            person.setAddress(address);
            long id = personService.create(person).getId();
            redirectAttributes.addFlashAttribute("message", "Person '" + id + "' was succesfully added");
            return "redirect:/persons";
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personBindingResult.rejectValue("birthNumber", "error.person", "Person with this birth number already exists!");
            return "person/new";
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

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long personId, Model model, final RedirectAttributes redirectAttributes) {
        Person person = personService.findById(personId);
        if (person == null) {
            redirectAttributes.addFlashAttribute("message", "Person '" + personId + "' doesn't exist.");
            return "redirect:/persons";
        }

        model.addAttribute("person", person);
        return "person/show";
    }


    @RequestMapping(value = "/persons/edit/{id}", method = RequestMethod.GET)
    public String editPerson(@PathVariable("id") Long personId, Model model,
                             final RedirectAttributes redirectAttributes) {
        Person person = personService.findById(personId);
        if (person == null) {
            redirectAttributes.addFlashAttribute("message", "Person '" + personId + "' doesn't exist.");
            return "redirect:/persons";
        }

        Address address = person.getAddress();
        model.addAttribute("person", person);
        model.addAttribute("address", address);
        return "person/edit";
    }

    @RequestMapping(value = {"/persons/edit/{id}"}, method = RequestMethod.POST)
    public String editPerson(@PathVariable("id") Long personId,
                             @Valid Person person,
                             BindingResult personBindingResult,
                             @Valid Address address,
                             BindingResult addressBindingResult,
                             final RedirectAttributes redirectAttributes) {


        if (personBindingResult.getErrorCount() != 1 || addressBindingResult.hasErrors()) {
            return "person/edit";
        }
        try {
            person.setId(personId);
            person.setAddress(address);
            long id = personService.update(person).getId();
            redirectAttributes.addFlashAttribute("message", "Person '" + id + "' was succesfully updated");
            return "redirect:/persons";
        } catch (PersonWithBirthNumberExists personWithBirthNumberExists) {
            personBindingResult.rejectValue("birthNumber", "error.person", "Person with this birth number already exists!");
            return "person/edit";
        } catch (PersonNotFound personNotFound) {
            redirectAttributes.addFlashAttribute("message", "Person '" + personId + "' doesn't exist");
            return "redirect:/persons";
        }
    }

}
