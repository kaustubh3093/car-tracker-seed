package io.egen.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.egen.entity.Alert;

public interface AlertRepository extends CrudRepository<Alert, String>{

	Optional<List<Alert>> findByVin(String vinID);
	
	Optional<List<Alert>> findByPriority(String priority);
	
	@Query("SELECT a FROM Alert a where a.priority LIKE %:key% and a.alertTime > :current ORDER BY a.make , a.model")
	Optional<List<Alert>> findHighAlertWithInLastTwoHour(@Param("key") String key, @Param("current") Date current);
	
	
}
