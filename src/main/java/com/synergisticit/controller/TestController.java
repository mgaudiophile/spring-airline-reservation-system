package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@Autowired
	UserService userService;
	
	@Autowired
	AirportService airportService;
	
	@GetMapping("/test")
	public String testInitUsers() {
		log.debug("TestController.test()......");
		
		List<Airport> airports = airportService.search("e");
		
		for (Airport a : airports) {
			log.debug(a.getAirportName() + ": " + a.getAirportCode());
		}
		
		return "test";
	}
}
