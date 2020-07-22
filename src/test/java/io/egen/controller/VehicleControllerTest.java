package io.egen.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.egen.entity.Vehicle;
import io.egen.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("application-integrationtest.properties")
public class VehicleControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private VehicleRepository repository;
	
	@Before
    public void setup() {
        Vehicle vehicle = new Vehicle();
        vehicle.setLastServiceDate(new Date());
        vehicle.setMake("HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setMaxFuelVolume(25);
        vehicle.setRedlineRpm(6500);
        vehicle.setVin("1234AQWSD34Fds");
        vehicle.setYear(2015);
        repository.save(vehicle);
    }

    @After
    public void cleanup() {
        repository.deleteAll();
    }
	@Test
	public void findAll() throws Exception{
		
		mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("1234AQWSD34Fds")));
	}
	
	@Test
	public void update() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		Vehicle vehicle = new Vehicle();
        vehicle.setLastServiceDate(new Date());
        vehicle.setMake("AUDI");
        vehicle.setModel("Q3");
        vehicle.setMaxFuelVolume(25);
        vehicle.setRedlineRpm(9500);
        vehicle.setVin("1234A12SD34Fds");
        vehicle.setYear(2019);

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(vehicle);
        
        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                                          .contentType(MediaType.APPLICATION_JSON)
                                          .content(mapper.writeValueAsBytes(vehicleList))
                   )
           .andExpect(MockMvcResultMatchers.status()
                                           .isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("1234A12SD34Fds")));


        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
           .andExpect(MockMvcResultMatchers.status()
                                           .isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
	}
	
	@Test
	public void getGeolocation() throws Exception {
		
	}
}
