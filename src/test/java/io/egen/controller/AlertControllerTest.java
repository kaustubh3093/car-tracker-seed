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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.egen.entity.Alert;
import io.egen.repository.AlertRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("application-integrationtest.properties")
public class AlertControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AlertRepository repository;
	
	@Before
    public void setup() {
		Alert alert = new Alert();
		alert.setAlertID("123alertONE");
		alert.setAlertTime(new Date());
		alert.setMake("HONDA");
		alert.setModel("ACCORD");
		alert.setMessage("Due to high Engine RPM");
		alert.setPriority("HIGH");
		alert.setVin("1234QWERLKJ123");
		
		repository.save(alert);
	}
	
	@After
    public void cleanup() {
        repository.deleteAll();
	}
	
	@Test
	public void findByVID() throws Exception{
		
		mvc.perform(MockMvcRequestBuilders.get("/alerts/historical/1234QWERLKJ123"))
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].vin", Matchers.is("1234QWERLKJ123")));
	}
	
	@Test
	public void findHighAlerts() throws Exception{
		
	}
}
