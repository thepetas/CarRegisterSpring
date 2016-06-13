package cz.thepetas.carregister.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import cz.thepetas.carregister.data.json.View;
import cz.thepetas.carregister.data.model.Address;
import cz.thepetas.carregister.data.model.Car;
import cz.thepetas.carregister.data.model.Vehicle;
import cz.thepetas.carregister.service.AddressService;
import cz.thepetas.carregister.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/rest/vehicles")
@RestController
public class VehicleRestController {

    @Autowired
    VehicleService vehicleService;

    @JsonView(View.SummaryFromCar.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    List<Vehicle> getAll() {
        return vehicleService.findAll();
    }

}
