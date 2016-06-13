package cz.thepetas.carregister.web.rest;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import cz.thepetas.carregister.data.json.View;
import cz.thepetas.carregister.data.model.Address;
import cz.thepetas.carregister.data.model.Person;
import cz.thepetas.carregister.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/addresses")
@RestController
public class AddressRestController {

    @Autowired
    AddressService addressService;

    @JsonView(View.SummaryFromAddress.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    List<Address> getAll() {
        return addressService.findAll();
    }

    @JsonView(View.SummaryFromAddress.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address getOne(@PathVariable("id") Long addressId) {
        return addressService.findById(addressId);
    }

}
