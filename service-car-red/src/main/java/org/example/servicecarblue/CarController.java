package org.example.servicecarblue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/red-car")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        car.setColor("Red");
        return carRepository.save(car);
    }
}
