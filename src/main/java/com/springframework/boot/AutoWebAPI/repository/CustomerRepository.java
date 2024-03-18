package com.springframework.boot.AutoWebAPI.repository;

import com.springframework.boot.AutoWebAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}