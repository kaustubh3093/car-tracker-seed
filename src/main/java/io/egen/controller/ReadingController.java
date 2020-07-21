package io.egen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Reading;
import io.egen.service.AlertService;
import io.egen.service.ReadingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/readings")
@Api(description = "Endpoints related to readings")
public class ReadingController {
	
	@Autowired
	private ReadingService readingService;

	@Autowired
	private AlertService alertService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new entry in the reading and alert table",
	   			  notes = "Create the entry in reading and alert table if vehicle with vinID exists in the vehicle table")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "No vehicle with vin present in vehicle table")
	})
	public Reading create(@RequestBody Reading reading) {
		alertService.create(reading);
        return readingService.create(reading);
    }
}
