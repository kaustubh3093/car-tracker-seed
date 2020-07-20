package io.egen.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.entity.Geolocation;
import io.egen.entity.Reading;
import io.egen.entity.Vehicle;
import io.egen.exception.VehicleNotFoundException;
import io.egen.repository.ReadingRepository;
import io.egen.repository.VehicleRepository;

@Service
public class ReadingServiceImpl implements ReadingService{

	@Autowired
	private ReadingRepository readingRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	/**
	 * Throw an exception if vehicle doesn't exist in vehicle which has the vinID equal to 
	 * vinID of Reading object
	 */
	@Override
	@Transactional
	public Reading create(Reading reading) {
		String vinID = reading.getVin();
		Optional<Vehicle> existingVehicle = vehicleRepository.findById(vinID);
		if (!existingVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id " + vinID + " doesn't exist.");
        }
		
		return readingRepository.save(reading);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Geolocation> getGeolocation(String vinID) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.MINUTE, -270);
		
		Date date = calender.getTime();
		
		Optional<List<Reading>> vehicleReading = readingRepository.findVehicleGeolocationWithinLastThirtyMin(vinID, date);
		
		if(!vehicleReading.isPresent()) {
			throw new VehicleNotFoundException("Vehicle with id: " + vinID + " has no geolocation in last 30 mins!");
		}
		
		List<Geolocation> geolocation = new ArrayList<>();
		vehicleReading.get().forEach(u -> geolocation.add(new Geolocation(u.getLatitude(), 
							u.getLongitude(), u.getTimestamp())));
		return geolocation;
	}

}
