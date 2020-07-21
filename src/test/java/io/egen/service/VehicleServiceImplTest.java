package io.egen.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import io.egen.entity.Vehicle;
import io.egen.repository.VehicleRepository;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

	@TestConfiguration
	static class VehicleServiceImplTestConfiguration {
		
		@Bean
		public VehicleService getService() {
			return new VehicleServiceImpl();
		}
	}
	
	@Autowired
	private VehicleService service;
	
	@MockBean
	private VehicleRepository repository;
	
	private List<Vehicle> vehicles;
	
	private String vin = "1231QWER1asd12";
	@Before
    public void setup() {
		Vehicle vehicle = new Vehicle();
		vehicle.setVin(vin);
		vehicle.setLastServiceDate(new Date());
		vehicle.setMake("HONDA");
		vehicle.setModel("ACCORD");
		vehicle.setRedlineRpm(6500);
		vehicle.setYear(2015);
		vehicle.setMaxFuelVolume(25);
		
		vehicles = Collections.singletonList(vehicle);
		
		Mockito.when(repository.findAll())
        	.thenReturn((List<Vehicle>)vehicles);
		
	}
	
	@After
    public void cleanup() {
    
	}
	 
	@Test
	public void findAll() {
		
		List<Vehicle> result = service.findAll();
		
		Assert.assertEquals("Vehicle list should match", vehicles, result);
	}
	
	@Test
	public void update() {
		
	}
	
	
}
