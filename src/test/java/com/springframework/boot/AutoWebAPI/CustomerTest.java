package com.springframework.boot.AutoWebAPI;

import com.springframework.boot.AutoWebAPI.model.Customer;
import com.springframework.boot.AutoWebAPI.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicles.add(vehicle);

        customer.setId(1L);
        customer.setFirstName("Abel");
        customer.setLastName("Barosanu");
        customer.setAge(30);
        customer.seteMail("abel.barosan@example.com");
        customer.setVehicle(vehicles);

        assertEquals(1L, customer.getId());
        assertEquals("Abel", customer.getFirstName());
        assertEquals("Barosanu", customer.getLastName());
        assertEquals(30, customer.getAge());
        assertEquals("abel.barosan@example.com", customer.geteMail());
        assertEquals(vehicles, customer.getVehicle());
    }

    @Test
    public void testToString() {
        Customer customer = new Customer();
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicles.add(vehicle);

        customer.setId(1L);
        customer.setFirstName("Abel");
        customer.setLastName("Barosanu");
        customer.setAge(30);
        customer.seteMail("abel.barosan@example.com");
        customer.setVehicle(vehicles);

        String expected = "Customer{id=1, firstName='Abel', lastName='Barosanu', age=30, eMail='abel.barosan@example.com', vehicle='1'}";
        assertEquals(expected, customer.toString());
    }
}
