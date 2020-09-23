package com.synergisticit.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Itinerary {

	private String flightNumber;
	private String departCity;
	private String arriveCity;
	private String departAirportCode;
	private String arriveAirportCode;
	private LocalDateTime departDateTime;
	private LocalDateTime arriveDateTime;
	private String duration;
}
