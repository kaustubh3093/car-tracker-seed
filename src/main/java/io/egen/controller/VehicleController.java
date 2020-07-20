package io.egen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Geolocation;
import io.egen.entity.Vehicle;
import io.egen.service.ReadingService;
import io.egen.service.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ReadingService readingService;
	
	@RequestMapping(method = RequestMethod.PUT)
	public List<Vehicle> update(@RequestBody List<Vehicle> vehicle) {
        return vehicleService.update(vehicle);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Vehicle> findAll(){
		return vehicleService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/geolocation/{vinID}")
	public List<Geolocation> getGeolocation(@PathVariable("vinID") String vinID){
		return readingService.getGeolocation(vinID);
	}
	
}
