package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public Flight save(Flight flight) {
		
		return flightRepo.save(flight);
	}

	@Override
	public List<Flight> findAll() {
		
		return flightRepo.findAll();
	}

	@Override
	public Flight findById(long id) {
		
		Optional<Flight> opt = flightRepo.findById(id);
		
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean existsById(long id) {
		
		return flightRepo.existsById(id);
	}

	@Override
	public void deleteById(long id) {
		
		flightRepo.deleteById(id);
	}

	@Override
	public List<Flight> findByDepartureCode(long departId, long arriveId) {
		
		return flightRepo.findByDepartureCode(departId, arriveId);
	}
}
