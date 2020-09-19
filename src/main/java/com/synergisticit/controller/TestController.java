package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	public String testInitUsers() {
		log.debug("TestController.test()......");
		
		
		
		return "test";
	}
}
