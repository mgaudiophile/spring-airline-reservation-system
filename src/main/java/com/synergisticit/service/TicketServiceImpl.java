package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Ticket;
import com.synergisticit.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepo;
	
	@Override
	public Ticket save(Ticket ticket) {
		
		return ticketRepo.save(ticket);
	}

	@Override
	public List<Ticket> findAll() {
		
		return ticketRepo.findAll();
	}

	@Override
	public Ticket findById(long id) {
		Optional<Ticket> opt = ticketRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean existsById(long id) {
		
		return ticketRepo.existsById(id);
	}

	@Override
	public void deleteById(long id) {
		
		ticketRepo.deleteById(id);
	}

	@Override
	public List<Ticket> findAllByCustomerId(long customerId) {
		
		return ticketRepo.findAllByCustomerId(customerId);
	}

}
