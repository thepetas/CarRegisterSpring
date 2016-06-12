package cz.thepetas.carregister.controller;

import cz.thepetas.carregister.exception.AddressNotFound;
import cz.thepetas.carregister.model.Address;
import cz.thepetas.carregister.service.AddressService;
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
public class AddressController extends WebMvcConfigurerAdapter {

    @Autowired
    private AddressService addressService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addresses").setViewName("/addresses");
    }


    @RequestMapping(value = "/addresses/new", method = RequestMethod.GET)
    public String newAddress(Address address) {
        return "address/new";
    }

    @RequestMapping(value = {"/addresses/new"}, method = RequestMethod.POST)
    public String newAddress(@Valid Address address,
                             BindingResult bindingResult,
                             final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "address/new";
        }

        long id = addressService.create(address).getId();

        redirectAttributes.addFlashAttribute("message", "Address '" + id + "' was succesfully added");
        return "redirect:/addresses";
    }

    @RequestMapping("/addresses")
    public String index(Model model) {
        model.addAttribute("addresses", addressService.findAll());
        return "address/index";
    }

    @RequestMapping(value = "/addresses/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long addressId, final RedirectAttributes redirectAttributes) {
        try {
            addressService.delete(addressId);
            redirectAttributes.addFlashAttribute("message", "Address '" + addressId + "' was deleted.");

        } catch (AddressNotFound addressNotFound) {
            redirectAttributes.addFlashAttribute("message", "Address '" + addressId + "' doesn't exist.");
        }

        return "redirect:/addresses";
    }
}
