package io.egen.service;

import java.util.Date;
import java.util.Optional;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.VehicleNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.VehicleRepository;
import io.egen.rules.HighPriorityRule;

@Service
public class AlertServiceImpl implements AlertService{

	private String message;
	private String priority;
	private String vin;
	private Date alertTime;
	
	private Rules rules;
	
	private Facts facts;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	@Override
	public void create(Reading reading) {
		
		vin = reading.getVin();
		Optional<Vehicle> existingVehicle = vehicleRepository.findById(vin);
		if (!existingVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id " + vin + " doesn't exist.");
        }
		
		
		rules = new Rules();
		HighPriorityRule highPriorityRule = new HighPriorityRule(); 
		rules.register(highPriorityRule);
		facts = new Facts();
		
		/**
		 * Check If Priority is High: If engine RPM greater than Red line RPM
		 * Create Entry in alert table
		 */
		facts.put("high-priority", reading.getEngineRpm() > existingVehicle.get().getRedlineRpm());
		
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
		
		if(highPriorityRule.getPriority() != null && highPriorityRule.getPriority().equals("HIGH")) {
			Alert alert = new Alert();
			alert.setVin(vin);
			alert.setMessage(highPriorityRule.getMessage());
			alert.setPriority(highPriorityRule.getPriority());
			alert.setAlertTime(reading.getTimestamp());
			alertRepository.save(alert);
		}
		
		/*Calling for medium alert rule*/
		if(reading.getFuelVolume() < 0.1 * existingVehicle.get().getMaxFuelVolume())
			System.out.println("The priority is Medium with fuel volume less than 10% of max fuel volume");
		
		
		/*Calling for low alert rule*/
		if(reading.isCheckEngineLightOn() || reading.isEngineCoolantLow())
			System.out.println("The priorty is low and either light on or engine coolent low");
	}

}
 