package com.example.demo;

import com.example.demo.models.RedCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "red-car-service", url = "http://carred:8082/red-car")
public interface RedCarClient {

    @GetMapping
    List<RedCar> getAllCars();

    @PostMapping
    RedCar saveCar(@RequestBody RedCar car);
}
