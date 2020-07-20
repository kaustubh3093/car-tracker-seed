package io.egen.service;

import java.util.List;
import io.egen.entity.Vehicle;

public interface VehicleService {

	List<Vehicle> update(List<Vehicle> vehicle);
	
	List<Vehicle> findAll();
}
