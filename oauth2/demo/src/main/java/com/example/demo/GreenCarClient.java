package com.example.demo;

import com.example.demo.models.GreenCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "green-car-service", url = "http://cargreen:8083/green-car")
public interface GreenCarClient {

    @GetMapping
    List<GreenCar> getAllCars();

    @PostMapping
    GreenCar saveCar(@RequestBody GreenCar car);
}
