package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Register {

	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zip;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String ssn;
	private String gender;
	private String mobile;
	private String email;
	private String username;
	private String password;
}
