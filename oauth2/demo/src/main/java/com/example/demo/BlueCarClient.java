package com.example.demo;

import com.example.demo.models.BlueCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "blue-car-service", url = "http://carblue:8081/blue-car")
public interface BlueCarClient {

    @GetMapping
    List<BlueCar> getAllCars();

    @PostMapping
    BlueCar saveCar(@RequestBody BlueCar car);
}
