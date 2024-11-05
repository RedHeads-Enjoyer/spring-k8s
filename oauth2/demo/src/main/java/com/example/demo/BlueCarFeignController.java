package com.example.demo;

import com.example.demo.models.BlueCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlueCarFeignController {
    private final BlueCarClient blueCarClient;
    @Autowired
    public BlueCarFeignController(BlueCarClient blueCarClient) {
        this.blueCarClient = blueCarClient;
    }

    @GetMapping("blue-car")
    public List<BlueCar> getBlueCars() {
        return blueCarClient.getAllCars();
    }

    @PostMapping("blue-car")
    public BlueCar addBlueCar(@RequestBody BlueCar blueCar) {
        return blueCarClient.saveCar(blueCar);
    }
}
