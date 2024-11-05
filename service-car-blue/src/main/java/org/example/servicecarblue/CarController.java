package org.example.servicecarblue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blue-car")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @GetMapping
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @PostMapping
    public Car save(@RequestBody Car car) {
        car.setColor("Blue");
        return carRepository.save(car);
    }
}
