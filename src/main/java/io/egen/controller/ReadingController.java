package io.egen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.entity.Reading;
import io.egen.service.AlertService;
import io.egen.service.ReadingService;

@RestController
@RequestMapping(value = "/readings")
public class ReadingController {
	
	@Autowired
	private ReadingService readingService;

	@Autowired
	private AlertService alertService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Reading create(@RequestBody Reading reading) {
		alertService.create(reading);
        return readingService.create(reading);
    }
}
