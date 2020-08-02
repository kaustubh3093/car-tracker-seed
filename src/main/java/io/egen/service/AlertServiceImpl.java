package io.egen.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.amazon.AmazonSESSample;
import io.egen.amazon.AmazonSNSSample;
import io.egen.entity.Alert;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.NoPreviousAlertException;
import io.egen.exception.NoPreviousHIGHAlertException;
import io.egen.exception.VehicleNotFoundException;
import io.egen.repository.AlertRepository;
import io.egen.repository.VehicleRepository;
import io.egen.rules.EngineRule;
import io.egen.rules.HighPriorityRule;
import io.egen.rules.MediumPriorityRule;
import io.egen.rules.TirePressureRule;

@Service
public class AlertServiceImpl implements AlertService{

	private String vin;
	
	private Rules rules;
	
	private Facts facts;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	@Override
	@Transactional
	public void create(Reading reading) {
		
		vin = reading.getVin();
		Optional<Vehicle> existingVehicle = vehicleRepository.findById(vin);
		if (!existingVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id " + vin + " doesn't exist.");
        }
		String make = existingVehicle.get().getMake();
		String model = existingVehicle.get().getModel();
		
		rules = new Rules();
		
		// Declare an instance of all the rule class
		HighPriorityRule highPriorityRule = new HighPriorityRule(); 
		MediumPriorityRule mediumPriorityRule = new MediumPriorityRule();
		TirePressureRule tirePressureRule = new TirePressureRule();
		EngineRule engineRule = new EngineRule();
		
		// Register instances to the rule
		rules.register(highPriorityRule);
		rules.register(mediumPriorityRule);
		rules.register(tirePressureRule);
		rules.register(engineRule);
		
		facts = new Facts();
		facts.put("high-priority", new int[] {reading.getEngineRpm() ,existingVehicle.get().getRedlineRpm()});
		facts.put("medium-priority", new float[] {reading.getFuelVolume(), existingVehicle.get().getMaxFuelVolume()});
		facts.put("low-priority-tire", new int[] {reading.getTires().getFrontLeft(), reading.getTires().getFrontRight(), reading.getTires().getRearLeft(), reading.getTires().getRearRight()});
		facts.put("low-priority-engine", new boolean[] {reading.isEngineCoolantLow(), reading.isCheckEngineLightOn()});
		
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);
		
		/**
		 * Check If Priority is High: If engine RPM greater than Red line RPM
		 * Create Entry in alert table
		 */
		if(highPriorityRule.getPriority() != null && highPriorityRule.getPriority().equals("HIGH")) {
			Alert alert = new Alert();
			alert.setVin(vin);
			alert.setMessage(highPriorityRule.getMessage());
			alert.setPriority(highPriorityRule.getPriority());
			alert.setAlertTime(reading.getTimestamp());
			alert.setMake(make);
			alert.setModel(model);
			alertRepository.save(alert);
			try {
			 AmazonSESSample.sendEmail();
			} catch (IOException e) {
			 e.printStackTrace();
			}
			//AmazonSNSClient snsClient = new AmazonSNSClient();
		    //String message = "High Alert Alarm: Your Engine RPM is exceeding the limit";
		    //String phoneNumber = "+16073041316";
		    //Map<String, MessageAttributeValue> smsAttributes = 
		    //        new HashMap<String, MessageAttributeValue>();
		    //MessageAttributeValue messageAtrValue = new MessageAttributeValue();
		    // Uncomment to send the messages
		    //AmazonSNSSample.sendSMSMessage(snsClient, message, phoneNumber, smsAttributes);
		}
		
		/**
		 * Check If Priority is Medium: IF fuel is less than 10 percentage of the fuel limit
		 * Create Entry in alert table
		 */
		if(mediumPriorityRule.getPriority() != null && mediumPriorityRule.getPriority().equals("MEDIUM")) {
			Alert alert = new Alert();
			alert.setVin(vin);
			alert.setMessage(mediumPriorityRule.getMessage());
			alert.setPriority(mediumPriorityRule.getPriority());
			alert.setAlertTime(reading.getTimestamp());
			alert.setMake(make);
			alert.setModel(model);
			alertRepository.save(alert);
		}
		
		/**
		 * Check If Priority is Low: If Tire pressure is not in the limit
		 * Create Entry in alert table
		 */
		if(tirePressureRule.getPriority() != null && tirePressureRule.getPriority().equals("LOW")) {
			Alert alert = new Alert();
			alert.setVin(vin);
			alert.setMessage(tirePressureRule.getMessage());
			alert.setPriority(tirePressureRule.getPriority());
			alert.setAlertTime(reading.getTimestamp());
			alert.setMake(make);
			alert.setModel(model);
			alertRepository.save(alert);
		}
		
		/**
		 * Check If Priority is Low: If Engine Coolant low or Engine light On
		 * Create Entry in alert table
		 */
		if(engineRule.getPriority() != null && engineRule.getPriority().equals("LOW")) {
			Alert alert = new Alert();
			alert.setVin(vin);
			alert.setMessage(engineRule.getMessage());
			alert.setPriority(engineRule.getPriority());
			alert.setAlertTime(reading.getTimestamp());
			alert.setMake(make);
			alert.setModel(model);
			alertRepository.save(alert);
		}
		
		
		// Remove instances from the rule
		rules.unregister(highPriorityRule);
		rules.unregister(mediumPriorityRule);
		rules.unregister(tirePressureRule);
		rules.unregister(engineRule);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findByVID(String vinID) {
		
		Optional<List<Alert>> totalAlert = alertRepository.findByVin(vinID);
		
		if(!totalAlert.isPresent()) {
			throw new NoPreviousAlertException("Vehicle with id " + vinID + " has no previous alerts!");
		}
		
		return totalAlert.get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alert> findHighAlerts() {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.HOUR, -2);
		
		Date date = calender.getTime();
		
		Optional<List<Alert>> highAlert = alertRepository.findHighAlertWithInLastTwoHour("HIGH",date);
		
		if(!highAlert.isPresent()) {
			throw new NoPreviousHIGHAlertException("No vehicle with priority HIGH in last 2 hours!");
		}
		
		return highAlert.get();
	}

}
 