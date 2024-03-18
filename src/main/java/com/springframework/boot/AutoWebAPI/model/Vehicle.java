package com.springframework.boot.AutoWebAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;


@Entity
@Table(name = "PremiumVehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotFound
    private String brand;

    @NotFound
    private String model;
   // private Customer customer;

    private int year;

    private String fuel;

    private double engine_size;

    private String transmission;

    private double price;

    private String imagePath;

    private int quantity;

    //@OneToOne(mappedBy = "vehicle")
    //private Customer customer;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getEngine_size() {
        return engine_size;
    }

    public void setEngine_size(double engine_size) {
        this.engine_size = engine_size;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
