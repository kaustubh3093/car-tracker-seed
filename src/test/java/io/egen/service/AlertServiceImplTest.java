package io.egen.service;

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

import io.egen.entity.Alert;
import io.egen.repository.AlertRepository;
import io.egen.repository.VehicleRepository;

@RunWith(SpringRunner.class)
public class AlertServiceImplTest {
	
	@TestConfiguration
	static class AlertServiceImplTestConfiguration {
		
		@Bean
		public AlertService getService() {
			return new AlertServiceImpl();
		}
	}
	
	@Autowired
	private AlertService service;
	
	@MockBean
	private VehicleRepository vehicleRepository;
	
	@MockBean 
	private AlertRepository repository;
	
	private List<Alert> alerts;
	
	private String vin = "1231QWER1asd12";
	
	@Before
    public void setup() {
		
		Alert alert = new Alert();
		alert.setMake("HONDA");
		alert.setModel("ACCORD");
		alert.setAlertTime(new Date());
		alert.setMessage("HIGH alert due to engine RPM");
		alert.setPriority("HIGH");
		alert.setVin(vin);
		
		alerts = Collections.singletonList(alert);
		
		Mockito.when(repository.findByVin(vin))
         .thenReturn(Optional.of(alerts));
		
	}
	
	@After
    public void cleanup() {
    }
	
	@Test
	public void findByVID() throws Exception{
		List<Alert> result = service.findByVID(vin);
		
		Assert.assertEquals("Alert list should match", alerts, result);
		
	}
	
	@Test
	public void findHighAlerts() throws Exception{
		
	}
	
	@Test
	public void create() throws Exception{
		
	}

}
