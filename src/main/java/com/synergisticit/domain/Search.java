package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Search {

	private String fromInput;
	private String toInput;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate depart;
	private Long tickets;
}
