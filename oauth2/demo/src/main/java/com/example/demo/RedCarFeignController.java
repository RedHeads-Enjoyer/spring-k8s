package com.example.demo;

import com.example.demo.models.RedCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedCarFeignController {
    private final RedCarClient redCarClient;
    @Autowired
    public RedCarFeignController(RedCarClient redCarClient) {
        this.redCarClient = redCarClient;
    }

    @GetMapping("red-car")
    public List<RedCar> getRedCars() {
        return redCarClient.getAllCars();
    }

    @PostMapping("red-car")
    public RedCar addRedCar(@RequestBody RedCar redCar) {
        return redCarClient.saveCar(redCar);
    }
}
