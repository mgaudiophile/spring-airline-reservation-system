package com.synergisticit.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.synergisticit.domain.Register;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.RegisterValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {

	private AirlineUtilities airUtil;
	private RegisterValidator registerValid;
	
	public RegisterController(AirlineUtilities airUtil, 
								RegisterValidator registerValid) {
		
		this.airUtil = airUtil;
		this.registerValid = registerValid;
	}
	
	@InitBinder("register")
	public void initRegisterValidatorBinder(WebDataBinder binder) {
		binder.addValidators(registerValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/register")
	public String actionRegister(@Valid @ModelAttribute Register register, BindingResult br, Model model) {
		log.debug("RegisterController.actionRegister().....");
		
		String message = null;
		
		if (!br.hasErrors()) {
			airUtil.registerNewUser(register);
			model.addAttribute("register", new Register());
			
			message = "Registration Successful. Please Log In.";
			model.addAttribute("registerMessage", message);
		} 
		
		airUtil.buildModel(model);
		return "login";
	}
	
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Register register() {
		return new Register();
	}
}
