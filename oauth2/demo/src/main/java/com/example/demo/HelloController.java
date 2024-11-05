package com.example.demo;

import com.example.demo.models.BlueCar;
import com.example.demo.models.GreenCar;
import com.example.demo.models.RedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HelloController {

    private final BlueCarClient blueCarClient;
    private final GreenCarClient greenCarClient;
    private final RedCarClient redCarClient;

    @Autowired
    public HelloController(BlueCarClient blueCarClient, GreenCarClient greenCarClient, RedCarClient redCarClient) {
        this.blueCarClient = blueCarClient;
        this.greenCarClient = greenCarClient;
        this.redCarClient = redCarClient;
    }


    @GetMapping("/info")
    public String info(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("username", principal.getName());

        List<BlueCar> blueCars = blueCarClient.getAllCars();
        model.addAttribute("blueCars", blueCars);

        List<GreenCar> greenCars = greenCarClient.getAllCars();
        model.addAttribute("greenCars", greenCars);

        List<RedCar> redCars = redCarClient.getAllCars();
        model.addAttribute("redCars", redCars);

        return "index";
    }

    @PostMapping("/addBlueCar")
    public String addBlueCar(@RequestParam String brand, @RequestParam String model) {
        BlueCar newCar = new BlueCar();
        newCar.setBrand(brand);
        newCar.setModel(model);

        blueCarClient.saveCar(newCar);

        return "back";
    }

    @PostMapping("/addGreenCar")
    public String addGreenCar(@RequestParam String brand, @RequestParam String model) {
        GreenCar newCar = new GreenCar();
        newCar.setBrand(brand);
        newCar.setModel(model);

        greenCarClient.saveCar(newCar);

        return "back";
    }

    @PostMapping("/addRedCar")
    public String addRedCar(@RequestParam String brand, @RequestParam String model) {
        RedCar newCar = new RedCar();
        newCar.setBrand(brand);
        newCar.setModel(model);

        redCarClient.saveCar(newCar);

        return "back";
    }
}

