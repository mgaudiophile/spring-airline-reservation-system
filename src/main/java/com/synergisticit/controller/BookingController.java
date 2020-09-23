package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookingController {

	
	
	@GetMapping("/booking")
	public String booking() {
		log.debug("BookingController.booking().....");
		
		
		return "booking";
	}
}
