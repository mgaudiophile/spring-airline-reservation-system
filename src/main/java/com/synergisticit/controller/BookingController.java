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
import com.synergisticit.validator.PaymentValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookingController {

	private AirlineUtilities airUtil;
	private PassengerValidator passengerValid;
	private PaymentValidator paymentValid;
	
	public BookingController(AirlineUtilities airUtil,
								PassengerValidator passengerValid,
								PaymentValidator paymentValid) {
		this.airUtil = airUtil;
		this.passengerValid = passengerValid;
		this.paymentValid = paymentValid;
	}
	
	@InitBinder("passenger")
	public void initPassengerValidBinder(WebDataBinder binder) {
		binder.addValidators(passengerValid);
	}
	
	@InitBinder("payment")
	public void initPaymentValidBinder(WebDataBinder binder) {
		binder.addValidators(paymentValid);
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
			airUtil.updateBookingModel(passenger, model);
			model.addAttribute("passenger", new Passenger());
		}
	
		return "booking";
	}
	
	@PostMapping("/purchase")
	public String purchase(@Valid @ModelAttribute Payment payment, BindingResult br, Model model, HttpSession session) {
		log.debug("BookingController.purchase().....");
		
		if (!br.hasErrors()) {
			airUtil.purchaseTicket(payment, (Long) session.getAttribute("flightId"));
			model.addAttribute("payment", new Payment());
			airUtil.buildCustomerModel(model);
			return "itinerary";
		}
		
		airUtil.buildCustomerModel(model);
		return "booking";
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
