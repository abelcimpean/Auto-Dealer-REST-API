package com.springframework.boot.AutoWebAPI.controller;

import com.springframework.boot.AutoWebAPI.dto.CustomerDto;
import com.springframework.boot.AutoWebAPI.exception.CustomerNotFoundException;
import com.springframework.boot.AutoWebAPI.model.Customer;
import com.springframework.boot.AutoWebAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicleCustomers/")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("createCustomer")
    public ResponseEntity<Customer> create(@RequestBody @Valid CustomerDto createDto){

        Customer response = customerService.createCustomer(createDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getAllCustomers")
    public ResponseEntity<List<Customer>> getCustomers(){

        List<Customer> customers = customerService.getCustomers();

        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {

        Customer response = customerService.getCustomer(id);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PutMapping("updateCustomer/{id}")
    public ResponseEntity updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid CustomerDto updateDto) throws CustomerNotFoundException {

        customerService.updateCustomer(id, updateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteCustomer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id){

        customerService.deleteCustomer(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}