package com.springframework.boot.AutoWebAPI.repository;

import com.springframework.boot.AutoWebAPI.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle , Long> {

    void deleteByBrand(String brand);
    void deleteByIdAndBrand(long id, String brand);

    //@Query(value = "SELECT Brand,Model from Vehicle ", nativeQuery = true)
    @Query("select vehicle.brand,vehicle.model from Vehicle vehicle")
    Vehicle findByBrandAndAndModel(String brand, String model);
}
