package com.synergisticit.domain;


import java.time.LocalDate;

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
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	private String name;
	
	@Embedded
	private Address customerAddress;
	
	private String phone;
	private String email;
	
	private LocalDate dob;
	private String ssn;
	private String gender;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
}
