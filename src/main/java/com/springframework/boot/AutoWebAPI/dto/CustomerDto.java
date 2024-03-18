package com.springframework.boot.AutoWebAPI.dto;


import com.springframework.boot.AutoWebAPI.model.Vehicle;
import org.hibernate.annotations.NotFound;

import java.util.List;

public class CustomerDto {

    @NotFound
    private String firstName;

    @NotFound
    private String lastName;

    @NotFound
    private Integer age;

    @NotFound
    private String eMail;

    private List<Vehicle> vehicle;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        int k = 0;
        return "Customer DTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                ",vehcile'" + vehicle.get(k++).getId() +'\'' +
                '}';
    }
}