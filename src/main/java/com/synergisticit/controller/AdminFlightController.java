package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.User;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminFlightController {

	private AirlineUtilities airUtil;
	
	public AdminFlightController(AirlineUtilities airUtil) {
		this.airUtil = airUtil;
	}
	
	@PostMapping("/adminSaveFlight")
	public String adminSaveFlight(Model model) {
		log.debug("AdminFlightController.adminSaveFlight().....");
		
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateFlight")
	public String adminUpdateFlight(Model model) {
		log.debug("AdminFlightController.adminUpdateFlight().....");
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteFlight")
	public String adminDeleteFlight(Model model) {
		log.debug("AdminFlightController.adminDeleteFlight().....");
		
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	
	// --- MODEL ATTRIBUTES ---
	
	@ModelAttribute
	public User user() {
		return new User();
	}
	
	@ModelAttribute
	public Flight flight() {
		return new Flight();
	}
	
	@ModelAttribute
	public Airport airport() {
		return new Airport();
	}
}
