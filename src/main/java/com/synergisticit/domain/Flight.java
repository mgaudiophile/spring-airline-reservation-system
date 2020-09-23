package com.synergisticit.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long flightId;
	
	private String flightNumber;
	
	@OneToOne
	private Airline airline;
	
	@ManyToOne
	private Airport departFrom;
	
	@ManyToOne
	private Airport arriveAt;
	
	private LocalDateTime departureDateTime;
	private LocalDateTime arrivalDateTime;
	
	private Long price;
}
