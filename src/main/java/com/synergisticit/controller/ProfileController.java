package com.synergisticit.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.synergisticit.domain.Profile;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.ProfileValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {

	private AirlineUtilities airUtil;
	private ProfileValidator profValid;
	
	public ProfileController(AirlineUtilities airUtil, 
								ProfileValidator profValid) {
		this.airUtil = airUtil;
		this.profValid = profValid;
	}
	
	@InitBinder("profile")
	public void initProfileValidWebDataBinder(WebDataBinder binder) {
		binder.addValidators(profValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/profile")
	public String myProfile(Model model) {
		log.debug("ProfileController.myProfile().....");
		
		airUtil.buildCustomerModel(model);
		return "profile";
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@Valid Profile profile, BindingResult br, Model model) {
		
		if (!br.hasErrors()) {
			airUtil.updateProfile(profile);
			model.addAttribute("profile", new Profile());
		}
		
		airUtil.buildCustomerModel(model);
		return "profile";
	}
	
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Profile profile() {
		return new Profile();
	}
}
