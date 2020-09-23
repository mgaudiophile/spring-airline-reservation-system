package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.User;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	private AirlineUtilities airUtil;
	
	public AdminController(AirlineUtilities airUtil) {
		this.airUtil = airUtil;
	}
	
	@GetMapping
	public String admin(Model model) {
		log.debug("AdminController.admin().....");
		
		airUtil.buildModel(model);
		return "admin";
	}
	
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
