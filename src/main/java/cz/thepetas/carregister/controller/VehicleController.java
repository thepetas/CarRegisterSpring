package cz.thepetas.carregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class VehicleController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/vehicles").setViewName("/vehicles");
    }

    @RequestMapping("/vehicles")
    public String index(Model model) {
//        model.addAttribute("vehicles", )
        return "vehicle/index";
    }
}
