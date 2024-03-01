package com.springframework.boot.AutoWebAPI.dto;

import org.hibernate.annotations.NotFound;

public class VehicleDto {

    @NotFound
    private String brand;
    @NotFound
    private String model;
    @NotFound
    private int year;
    @NotFound
    private String fuel;
    @NotFound
    private double engine_size;
    @NotFound
    private String transmission;
    @NotFound
    private double price;
    private String imagePath;

    @NotFound
    private int quantity;



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

    @Override
    public String toString() {
        return "VehicleDto{" +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuel='" + fuel + '\'' +
                ", engine_size=" + engine_size +
                ", transmission='" + transmission + '\'' +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
