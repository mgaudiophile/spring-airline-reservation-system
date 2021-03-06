package com.synergisticit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Register;

@Controller
public class LoginController {
	
	@GetMapping(value={"", "/", "home"})
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String loginUser(@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "error", required = false) String error, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String message = null;

		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			message = "You have been logged out successfully.";
			return "login";
		}

		if (error != null) {
			message = "Check your username and/or password.";
		}

		model.addAttribute("message", message);
		return "login";
	}

	@GetMapping("/403")
	public String forbidden() {
		return "403";
	}
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Register register() {
		return new Register();
	}
}
