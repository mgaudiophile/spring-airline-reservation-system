package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;

@Controller
public class UserController {
	
	@RequestMapping("/userForm")
	public String userForm(Model model) {
		
		System.out.println("UserController.userForm()...");
		
		return "user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(Model model) {
		System.out.println("UserController.saveUser()...");
		
		
		
		return "user";
	}
	
	@ModelAttribute
	public User user() {
		return new User();
	}
}
