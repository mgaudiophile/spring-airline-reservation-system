package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerRepository passRepo;
	
	@Override
	public Passenger save(Passenger passenger) {
		
		return passRepo.save(passenger);
	}

	@Override
	public List<Passenger> findAll() {
		
		return passRepo.findAll();
	}

	@Override
	public Passenger findById(long id) {
		Optional<Passenger> opt = passRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public void deleteById(long id) {
		
		passRepo.deleteById(id);
	}

	@Override
	public List<Passenger> findAllByTicketId(long ticketId) {
		
		return passRepo.findAllByTicketId(ticketId);
	}

}
