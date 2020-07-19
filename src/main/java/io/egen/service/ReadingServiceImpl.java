package io.egen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.entity.Reading;
import io.egen.repository.ReadingRepository;

@Service
public class ReadingServiceImpl implements ReadingService{

	@Autowired
	private ReadingRepository vehicleReadingRepository;
	
	@Override
	public Reading create(Reading vehicleReading) {
		
		return vehicleReadingRepository.save(vehicleReading);
	}

}
