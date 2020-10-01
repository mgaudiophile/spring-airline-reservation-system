package com.synergisticit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Search;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.SearchValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SearchFlightsController {

	private AirlineUtilities airUtil;
	private SearchValidator searchValid;
	
	public SearchFlightsController(AirlineUtilities airUtil,
									SearchValidator searchValid) {
		this.airUtil = airUtil;
		this.searchValid = searchValid;
	}
	
	@InitBinder("search")
	public void initSearchValidatorBinder(WebDataBinder binder) {
		binder.addValidators(searchValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/searchFlights")
	public String searchFlights(@Valid @ModelAttribute Search search, BindingResult br, Model model) {
		log.debug("SearchFlightsController.searchFlights().....");
		
		List<Flight> results = airUtil.searchFlights(search);
		if (!br.hasErrors() && !results.isEmpty()) {
			model.addAttribute("searchResults", results);
			model.addAttribute("tickets", search.getTickets());
		}
		
		airUtil.buildCustomerModel(model);
		return "customer";
	}
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Search search() {
		return new Search();
	}
}
