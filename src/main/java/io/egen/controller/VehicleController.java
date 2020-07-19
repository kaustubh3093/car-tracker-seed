package io.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Vehicle;
import io.egen.service.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(method = RequestMethod.PUT)
	public List<Vehicle> update(@RequestBody List<Vehicle> vehicle) {
        return vehicleService.update(vehicle);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> findAll(){
		return vehicleService.findAll();
	}
	
}
