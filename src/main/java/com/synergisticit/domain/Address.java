package com.synergisticit.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Embeddable
public class Address {

	@NotEmpty
	private String addressLine1;
	
	@NotEmpty
	private String addressLine2;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String zip;
}
