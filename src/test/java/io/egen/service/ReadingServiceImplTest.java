package io.egen.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import io.egen.entity.Geolocation;
import io.egen.entity.Reading;
import io.egen.repository.ReadingRepository;
import io.egen.repository.VehicleRepository;

@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {

	@TestConfiguration
	static class ReadingServiceImplTestConfiguration {
		
		@Bean
		public ReadingService getService() {
			return new ReadingServiceImpl();
		}
	}
	
	@Autowired
	private ReadingService service;
	
	@MockBean
	private ReadingRepository repository;
	
	@MockBean
	private VehicleRepository vehicleRepository;
	
	private List<Reading> readings;
	
	private String vin = "1234QWER12sa";
	
	Calendar calender = Calendar.getInstance();
	
	Date date;
	@Before
    public void setup() {
		calender.set(Calendar.MINUTE, -270);
		date = calender.getTime();
		Reading reading = new Reading();
		reading.setLatitude(12.123);
		reading.setLongitude(-12.123);
		reading.setTimestamp(new Date());
		
		readings = Collections.singletonList(reading);
		
		Mockito.when(repository.findVehicleGeolocationWithinLastThirtyMin(vin, date))
    		.thenReturn(Optional.of(readings));
	}
	
	@After
    public void cleanup() {
    }
	
	@Test
	public void getGeolocation() {
		List<Geolocation> result = service.getGeolocation(vin, date);
		
		Assert.assertEquals("Vehicle geolocation latitude should match", result.get(0).getLatitude(), 12.123, 0);
		Assert.assertEquals("Vehicle geolocation latitude should match", result.get(0).getLongitude(), -12.123, 0);
	}
	
	@Test
	public void create () {
		
	}
}
