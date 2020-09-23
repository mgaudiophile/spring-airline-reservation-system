package com.synergisticit.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	
	private String traveler;
	
	@Embedded
	private Itinerary itinerary;
	
	private String price;
	
	private String email;
	
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
}
