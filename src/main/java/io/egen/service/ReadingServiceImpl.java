package io.egen.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Reading create(Reading reading) {
		String vinID = reading.getVin();
		Optional<Vehicle> existingVehicle = vehicleRepository.findById(vinID);
		if (!existingVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with id " + vinID + " doesn't exist.");
        }
		
		return readingRepository.save(reading);
	}

}
