package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SearchFlightsController {

	
	
	@PostMapping("/searchFlights")
	public String searchFlights() {
		log.debug("SearchFlightsController.searchFlights().....");
		
		return "customer";
	}
}
