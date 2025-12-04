package com.springframework.boot.AutoWebAPI.controller;

import com.springframework.boot.AutoWebAPI.dto.VehicleDto;
import com.springframework.boot.AutoWebAPI.exception.VehicleNotFoundException;
import com.springframework.boot.AutoWebAPI.model.Vehicle;
import com.springframework.boot.AutoWebAPI.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicles/")
@CrossOrigin //cors policy for frontend so they can use the endpoints/methods
public class VehicleController {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public List<Vehicle> getAll() {
        return service.findAll();
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable("id") Long id) throws VehicleNotFoundException {
         Vehicle response = service.findById(id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
       service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("create")
    public Vehicle create(@RequestBody VehicleDto vehicleRequest) {
        return service.create(vehicleRequest);
    }

    @PutMapping("updateCar/{id}")
   public ResponseEntity updateVehicle(@PathVariable("id") Long id,@RequestBody @Valid VehicleDto updateVehicleRequest) throws VehicleNotFoundException {

        service.updateVehicle(id,updateVehicleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("findByBrandAndModel/{Brand}/{Model}")
    public ResponseEntity<Vehicle> findByBrandAndModel(@PathVariable("Brand") String brand,@PathVariable("Model") String model){

        Vehicle response = service.findByBrandAndModel(brand,model);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
