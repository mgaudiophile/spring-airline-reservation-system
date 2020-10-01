package com.synergisticit.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Payment;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.PassengerValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookingController {

	private AirlineUtilities airUtil;
	private PassengerValidator passengerValid;
	
	public BookingController(AirlineUtilities airUtil,
								PassengerValidator passengerValid) {
		this.airUtil = airUtil;
		this.passengerValid = passengerValid;
	}
	
	@InitBinder("passenger")
	public void initPassengerValidBinder(WebDataBinder binder) {
		binder.addValidators(passengerValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/booking")
	public String booking(@RequestParam long flightId, @RequestParam long tickets, @RequestParam long total, Model model, HttpSession session) {
		log.debug("BookingController.booking()....." + tickets);

		airUtil.initBookingModel(flightId, tickets, total, model);
		airUtil.resetPassengers();
		session.setAttribute("flightId", flightId);
		session.setAttribute("tickets", tickets);
		session.setAttribute("total", total);
		
		return "booking";
	}
	
	@PostMapping("/savePassenger")
	public String savePassenger(@Valid @ModelAttribute Passenger passenger, BindingResult br, Model model) {
		log.debug("BookingController.savePassenger().....");
		
		if (!br.hasErrors()) {
			airUtil.addPassenger(passenger);
			model.addAttribute("passenger", new Passenger());
		}
	
		airUtil.updateBookingModel(passenger, model);
		return "booking";
	}
	
	@PostMapping("/purchase")
	public String purchase(@ModelAttribute Payment payment, Model model, HttpSession session) {
		log.debug("BookingController.purchase().....");
		
		airUtil.purchaseTicket(payment, (Long) session.getAttribute("flightId"));
		
		airUtil.buildCustomerModel(model);
		return "itinerary";
	}
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Passenger passenger() {
		return new Passenger();
	}
	
	@ModelAttribute
	public Payment payment() {
		return new Payment();
	}
}
