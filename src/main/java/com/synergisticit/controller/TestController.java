package com.synergisticit.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.UserService;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@Autowired
	AirlineUtilities airUtil;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	AirlineService airlineService;
	
	@GetMapping("/test")
	public String testInitUsers() {
		log.debug("TestController.test()......");
		
//		List<Flight> res = flightService.findAll();
//		for (Flight f : res) {
//			airUtil.prettifyDateTimeETA(f);
//			flightService.save(f);
//		}
//		Flight f = flightService.findById(1);
//		
//		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM d");
//		log.debug(f.getDepartureDateTime().format(dateFormatter));
		//String depart = LocalTime.from(timeFormatter.parse(flight.getDepartureDateTime().toLocalTime()))).isBefore(LocalTime.NOON));
		
		return "test";
	}
}
