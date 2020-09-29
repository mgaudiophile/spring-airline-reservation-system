package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Ticket;

public interface TicketService {

	Ticket save(Ticket ticket);
	
	List<Ticket> findAll();
	Ticket findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
	
	List<Ticket> findAllByCustomerId(long customerId);
}
