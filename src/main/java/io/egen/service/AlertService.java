package io.egen.service;

import java.util.List;

import io.egen.entity.Alert;
import io.egen.entity.Reading;

public interface AlertService {

	void create(Reading reading);
	
	List<Alert> findByVID(String vinID);

	List<Alert> findHighAlerts();
}
