package io.egen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Alert;
import io.egen.service.AlertService;

@RestController
@RequestMapping(value = "/alerts")
public class AlertController {

	@Autowired
	 private AlertService alertService;
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/historical/{vinID}")
	 public List<Alert> findByVID(@PathVariable("vinID") String vinID) {
	        return alertService.findByVID(vinID);
	    }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/high")
	 public List<Alert> findHighAlerts() {
	        return alertService.findHighAlerts();
	 }
	
}
