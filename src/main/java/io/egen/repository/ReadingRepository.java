package io.egen.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.egen.entity.Alert;
import io.egen.entity.Reading;


public interface ReadingRepository extends CrudRepository<Reading, String>{

	Optional<List<Reading>> findByVin(String vinID);
	
	@Query("SELECT read FROM Reading read where read.vin LIKE %:vin% and read.timestamp > :current ORDER BY read.timestamp")
	Optional<List<Reading>> findVehicleGeolocationWithinLastThirtyMin(@Param("vin") String vin, @Param("current") Date current);
	
	
}
