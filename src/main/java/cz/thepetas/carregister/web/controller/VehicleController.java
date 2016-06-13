package cz.thepetas.carregister.web.controller;

import cz.thepetas.carregister.data.enums.VehicleType;
import cz.thepetas.carregister.exception.CarWithIdMarkExists;
import cz.thepetas.carregister.exception.VehicleNotFound;
import cz.thepetas.carregister.data.model.Car;
import cz.thepetas.carregister.data.model.Person;
import cz.thepetas.carregister.service.PersonService;
import cz.thepetas.carregister.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class VehicleController extends WebMvcConfigurerAdapter {


    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PersonService personService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/vehicles").setViewName("/vehicles");
    }

    @RequestMapping("/vehicles")
    public String index(Model model) {
        model.addAttribute("cars", vehicleService.findAllByType(VehicleType.CAR));
        return "vehicle/index";
    }

    @RequestMapping(value = "/vehicles/new", method = RequestMethod.GET)
    public String newVehicle(Car car, Person person) {
        return "vehicle/new";
    }


    @RequestMapping(value = {"/vehicles/new"}, method = RequestMethod.POST)
    public String newPerson(@Valid Car car,
                            BindingResult carBindingResult,
                            @Valid Person person,
                            BindingResult personBindingResult,
                            final RedirectAttributes redirectAttributes) {


        if (carBindingResult.hasErrors()) {
            return "vehicle/new";
        }

        try {
            if (person.getId() != null) {
                long personId = person.getId();
                person = personService.findById(personId);
                if (person == null) {
                    personBindingResult.rejectValue("id", "error.person", "Owner with this id doesn't exist!");
                    return "vehicle/new";
                }
                car.setOwner(person);
            }

            long id = vehicleService.create(car).getId();
            redirectAttributes.addFlashAttribute("message", "Car '" + id + "' was succesfully added");
            return "redirect:/vehicles";
        } catch (CarWithIdMarkExists personWithBirthNumberExists) {
            carBindingResult.rejectValue("idMark", "error.car", "Car with this id mark already exists!");
            return "vehicle/new";
        }
    }


    @RequestMapping(value = "/vehicles/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long vehicleId, final RedirectAttributes redirectAttributes) {
        try {
            vehicleService.delete(vehicleId);
            redirectAttributes.addFlashAttribute("message", "Vehicle '" + vehicleId + "' was deleted.");
        } catch (VehicleNotFound vehicleNotFound) {
            redirectAttributes.addFlashAttribute("message", "Vehicle '" + vehicleId + "' doesn't exist.");
        }

        return "redirect:/vehicles";
    }

    @RequestMapping(value = "/vehicles/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long vehicleId, Model model, final RedirectAttributes redirectAttributes) {
        Car car = (Car) vehicleService.findById(vehicleId);
        if (car == null) {
            redirectAttributes.addFlashAttribute("message", "Vehicle '" + vehicleId + "' doesn't exist.");
            return "redirect:/vehicles";
        }

        model.addAttribute("car", car);
        if (car.getOwner() != null) {
            model.addAttribute("owner", car.getOwner());
        }
        return "vehicle/show";
    }


    @RequestMapping(value = "/vehicles/edit/{id}", method = RequestMethod.GET)
    public String editVehicle(@PathVariable("id") Long vehicleId, Model model,
                              final RedirectAttributes redirectAttributes) {
        Car car = (Car) vehicleService.findById(vehicleId);
        if (car == null) {
            redirectAttributes.addFlashAttribute("message", "Vehicle '" + vehicleId + "' doesn't exist.");
            return "redirect:/vehicles";
        }


        model.addAttribute("car", car);
        if (car.getOwner() == null) {
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("person", car.getOwner());
        }
        return "vehicle/edit";
    }

    @RequestMapping(value = {"/vehicles/edit/{id}"}, method = RequestMethod.POST)
    public String editVehicle(@PathVariable("id") Long vehicleId,
                              @Valid Car car,
                              BindingResult carBindingResult,
                              @Valid Person person,
                              BindingResult personBindingResult,
                              final RedirectAttributes redirectAttributes) {


        if (carBindingResult.hasErrors()) {
            return "vehicle/edit";
        }

        try {
            car.setId(vehicleId);
            if (person.getId() != null) {
                long personId = person.getId();
                person = personService.findById(personId);
                if (person == null) {
                    personBindingResult.rejectValue("id", "error.person", "Owner with this id doesn't exist!");
                    return "vehicle/edit";
                }
                car.setOwner(person);
            } else {
                car.setOwner(null);
            }

            long id = vehicleService.update(car).getId();
            redirectAttributes.addFlashAttribute("message", "Car '" + id + "' was succesfully updated");
            return "redirect:/vehicles";
        } catch (CarWithIdMarkExists personWithBirthNumberExists) {
            carBindingResult.rejectValue("idMark", "error.car", "Car with this id mark already exists!");
            return "vehicle/edit";
        } catch (VehicleNotFound vehicleNotFound) {
            redirectAttributes.addFlashAttribute("message", "Car '" + vehicleId + "' doesn't exist!");
            return "redirect:/vehicles";
        }
    }
}
