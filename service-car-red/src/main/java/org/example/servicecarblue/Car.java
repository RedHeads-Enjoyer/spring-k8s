package org.example.servicecarblue;

import jakarta.persistence.*;

@Entity
@Table(name="red_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String color;

    public Car() {}

    public Long getId() {
        return id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }
}
