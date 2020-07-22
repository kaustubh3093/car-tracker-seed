package io.egen.controller;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.egen.entity.Reading;
import io.egen.entity.Tire;
import io.egen.entity.Vehicle;
import io.egen.repository.AlertRepository;
import io.egen.repository.ReadingRepository;
import io.egen.repository.VehicleRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("application-integrationtest.properties")
public class ReadingControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ReadingRepository repository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	@Before
    public void setup() {
		Vehicle vehicle = new Vehicle();
		vehicle.setLastServiceDate(new Date());
        vehicle.setMake("HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setMaxFuelVolume(25);
        vehicle.setRedlineRpm(6500);
        vehicle.setVin("1234QWERasd123");
        vehicle.setYear(2015);
        vehicleRepository.save(vehicle);
	}
	
	@After
    public void cleanup() {
        repository.deleteAll();
        vehicleRepository.deleteAll();
        alertRepository.deleteAll();
    }
	
	@Test
	public void create () throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Reading reading = new Reading();
		reading.setVehicleId("hondaAccord");
		reading.setVin("1234QWERasd123");
		reading.setLatitude(12.1234);
		reading.setLongitude(-12.1123);
		reading.setTimestamp(new Date());
		reading.setFuelVolume(12);
		reading.setEngineHp(10001);
		reading.setSpeed(187);
		reading.setEngineRpm(9899);
		reading.setCheckEngineLightOn(true);
		reading.setCruiseControlOn(false);
		reading.setEngineCoolantLow(true);
		reading.setTires(new Tire());
		
		mvc.perform(MockMvcRequestBuilders.post("/readings")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(mapper.writeValueAsBytes(reading))
                 )
				 .andExpect(MockMvcResultMatchers.status().isOk())
				 .andExpect(MockMvcResultMatchers.jsonPath("$.vehicleId", Matchers.is("hondaAccord")));
		
	}
	
	@Test
	public void create404 () throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Reading reading = new Reading();
		reading.setVehicleId("hondaAccord");
		reading.setVin("1234QWE111123");
		reading.setLatitude(12.1234);
		reading.setLongitude(-12.1123);
		reading.setTimestamp(new Date());
		reading.setFuelVolume(12);
		reading.setEngineHp(10001);
		reading.setSpeed(187);
		reading.setEngineRpm(9899);
		reading.setCheckEngineLightOn(true);
		reading.setCruiseControlOn(false);
		reading.setEngineCoolantLow(true);
		reading.setTires(new Tire());
		
		mvc.perform(MockMvcRequestBuilders.post("/readings")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(mapper.writeValueAsBytes(reading))
                 )
				 .andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
}
