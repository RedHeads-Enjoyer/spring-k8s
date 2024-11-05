package com.example.demo;

import com.example.demo.models.GreenCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreenCarFeignController {
    private final GreenCarClient greenCarClient;
    @Autowired
    public GreenCarFeignController(GreenCarClient greenCarClient) {
        this.greenCarClient = greenCarClient;
    }

    @GetMapping("green-car")
    public List<GreenCar> getGreenCars() {
        return greenCarClient.getAllCars();
    }

    @PostMapping("green-car")
    public GreenCar addGreenCar(@RequestBody GreenCar greenCar) {
        return greenCarClient.saveCar(greenCar);
    }
}
