package cz.thepetas.carregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarController {

    @RequestMapping("/cars")
    public String index() {
        return "car/index";
    }
}
