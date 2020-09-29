package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Search;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

	private AirlineUtilities airUtil;
	
	public CustomerController(AirlineUtilities airUtil) {
		this.airUtil = airUtil;
	}
	
	@GetMapping
	public String customer(Model model) {
		log.debug("CustomerController.customer().....");

			
		airUtil.buildCustomerModel(model);	
		return "customer";
	}
	
	@ModelAttribute
	public Search search() {
		return new Search();
	}
}
