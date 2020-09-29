package com.synergisticit.domain;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Embeddable
public class Passenger {

	private String name;
	private String gender;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;
	private String mobile;
	private String email;
	private Long ticketNumber;
}
