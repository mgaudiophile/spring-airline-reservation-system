package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Search;

@Component
public class SearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Search.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Search search = (Search) target;
		
		if (search.getFromInput().equals(search.getToInput())) {
			errors.rejectValue("toInput", "error", "please choose different airport.");
		}
		
		if (search.getTickets() == null || search.getTickets() < 0) {
			errors.rejectValue("tickets", "error", "quantity must be greater than 0.");
		}
	}

}
