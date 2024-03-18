package com.springframework.boot.AutoWebAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;

import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @NotFound
    private String firstName;

    @NotFound
    private String lastName;

    @NotFound
    private Integer age;

    @NotFound
    private String eMail;

    //PremiumVehicles_id
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_cust_id",referencedColumnName = "id")
    private List<Vehicle> vehicle;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="fk_")
//    private Vehicle vehicle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                ", vehicle='" + vehicle.get(k++).getId()+'\''+
                '}';
    }
}