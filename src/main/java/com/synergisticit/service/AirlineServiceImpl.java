package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airline;
import com.synergisticit.repository.AirlineRepository;

@Service
public class AirlineServiceImpl implements AirlineService {

	@Autowired
	AirlineRepository airlineRepo;
	
	@Override
	public Airline save(Airline airline) {
		
		return airlineRepo.save(airline);
	}

	@Override
	public List<Airline> findAll() {
		
		return airlineRepo.findAll();
	}

	@Override
	public Airline findById(long id) {
		
		Optional<Airline> opt = airlineRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean existsById(long id) {
		
		return airlineRepo.existsById(id);
	}

	@Override
	public void deleteById(long id) {
		
		airlineRepo.deleteById(id);
	}

}
