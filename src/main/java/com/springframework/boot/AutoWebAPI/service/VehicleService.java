package com.springframework.boot.AutoWebAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.boot.AutoWebAPI.dto.VehicleDto;
import com.springframework.boot.AutoWebAPI.exception.VehicleNotFoundException;
import com.springframework.boot.AutoWebAPI.model.Vehicle;
import com.springframework.boot.AutoWebAPI.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    //inversion of control(vehicleRepository obj gives control to VehicleService class) & dependency injection

    private final VehicleRepository vehicleRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,ObjectMapper objectMapper) {
        this.vehicleRepository = vehicleRepository;
        this.objectMapper = objectMapper;
    }

    public Vehicle create(VehicleDto vehicleRequest) {

        LOGGER.info("Creating object {}",vehicleRequest);

//        same thing as object mapper

//        Vehicle vehicle = new Vehicle();
//        vehicle.setBrand(vehicleRequest.getBrand());
//        vehicle.setModel(vehicleRequest.getModel());
//        vehicle.setYear(vehicleRequest.getYear());
//        vehicle.setFuel(vehicleRequest.getFuel());
//        vehicle.setEngine_size(vehicleRequest.getEngine_size());
//        vehicle.setTransmission(vehicleRequest.getTransmission());
//        vehicle.setPrice(vehicleRequest.getPrice());
//        vehicle.setImagePath(vehicleRequest.getImagePath());
//        vehicle.setQuantity(vehicleRequest.getQuantity());
//
//        return vehicleRepository.save(vehicle);

       Vehicle vehicle1 = objectMapper.convertValue(vehicleRequest,Vehicle.class);
//
        return vehicleRepository.save(vehicle1);
    }

    public void deleteById(long id) {
        vehicleRepository.deleteById(id);

    }

    //get all Vehicles from database
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    //get vehicle by certain ID
    public Vehicle findById(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(()->new VehicleNotFoundException("Vehicle "+id+" does not exist"));
    }

    //update a vehicle
    public Vehicle updateVehicle(long id, VehicleDto updateVehicleRequest) throws VehicleNotFoundException {
        Vehicle vehicle = findById(id);

        BeanUtils.copyProperties(updateVehicleRequest,vehicle);

        return vehicleRepository.save(vehicle);

    }

    public Vehicle findByBrandAndModel(String brand , String model){

       return vehicleRepository.findByBrandAndAndModel(brand,model);
    }

}
