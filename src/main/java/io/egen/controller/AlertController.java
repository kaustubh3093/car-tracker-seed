package io.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Alert;
import io.egen.service.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/alerts")
@Api(description = "Endpoints related to alerts")
public class AlertController {

	@Autowired
	 private AlertService alertService;
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/historical/{vinID}")
	 @ApiOperation(value = "Find All Historical Alerts for specific vehicle",
				   notes = "Returns the list of all the alerts for vin ID")
	 @ApiResponses(value = {
			 		@ApiResponse(code = 200, message = "OK"),
			 		@ApiResponse(code = 500, message = "Internal server error"),
			 		@ApiResponse(code = 404, message = "No previous Alerts")
	 })
	 public List<Alert> findByVID(@PathVariable("vinID") String vinID) {
	        return alertService.findByVID(vinID);
	    }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/high")
	 @ApiOperation(value = "Find All High alerts",
	   			notes = "Returns the list of all the high alerts in last two hours order by vehicle name")
	 @ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code = 404, message = "No high alerts in last 2 hours")
     })
	 public List<Alert> findHighAlerts() {
	        return alertService.findHighAlerts();
	 }
	
}
