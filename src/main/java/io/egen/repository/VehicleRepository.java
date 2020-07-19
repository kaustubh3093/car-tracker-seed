package io.egen.repository;

import org.springframework.data.repository.CrudRepository;

import io.egen.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String>{

}
