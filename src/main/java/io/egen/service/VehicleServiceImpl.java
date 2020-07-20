package io.egen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.entity.Vehicle;
import io.egen.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Override
	@Transactional
	public List<Vehicle> update(List<Vehicle> vehicle) {
		vehicle.forEach(v -> vehicleRepository.save(v));
		return vehicle;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vehicle> findAll() {
		return (List<Vehicle>) vehicleRepository.findAll();
	}

}
