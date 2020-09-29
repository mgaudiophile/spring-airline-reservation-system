package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IntineraryController {

	private AirlineUtilities airUtil;
	
	public IntineraryController(AirlineUtilities airUtil) {
		this.airUtil = airUtil;
	}
	
	@GetMapping("/itinerary")
	public String itinerary(Model model) {
		log.debug("IntineraryController.itinerary().....");
		
		airUtil.buildCustomerModel(model);
		return "itinerary";
	}
}
