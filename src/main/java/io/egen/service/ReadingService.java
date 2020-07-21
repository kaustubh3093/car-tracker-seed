package io.egen.service;

import java.util.Date;
import java.util.List;

import io.egen.entity.Geolocation;
import io.egen.entity.Reading;

public interface ReadingService {

	Reading create(Reading vehicleReading);

	List<Geolocation> getGeolocation(String vinID, Date date);
}
