package io.egen.controller;

import java.util.Calendar;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/vehicles")
@Api(description = "Endpoints related to vehicles")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ReadingService readingService;
	
	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update/Create the vehicle entry in databse",
				notes = "If vehicle with vin present in the database then update the entry or else create new one")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "OK"),
				@ApiResponse(code = 500, message = "Internal server error")
	})
	public List<Vehicle> update(@RequestBody List<Vehicle> vehicle) {
        return vehicleService.update(vehicle);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get all the vehicle entries",
				notes = "Fetch All the vehicle entry present in the database")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "OK"),
				@ApiResponse(code = 500, message = "Internal server error"),
				@ApiResponse(code = 404, message = "No vehicle entry in the database")
	})
	public List<Vehicle> findAll(){
		return vehicleService.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/geolocation/{vinID}")
	@ApiOperation(value = "Get the geolocation for the vehicle",
				notes = "Fetch the list of geolocations for vehicle in the last 30 mins")
	@ApiResponses(value = {
				@ApiResponse(code = 200, message = "OK"),
				@ApiResponse(code = 500, message = "Internal server error"),
				@ApiResponse(code = 404, message = "No vehicle entry in the database")
	})
	public List<Geolocation> getGeolocation(@PathVariable("vinID") String vinID){
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.MINUTE, -270);
		
		Date date = calender.getTime();
		return readingService.getGeolocation(vinID, date);
	}
	
}
