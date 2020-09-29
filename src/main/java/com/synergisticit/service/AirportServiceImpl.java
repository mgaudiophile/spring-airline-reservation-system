package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepository airportRepo;
	
	@Override
	public Airport save(Airport airport) {
		
		return airportRepo.save(airport);
	}

	@Override
	public List<Airport> findAll() {
		
		return airportRepo.findAll();
	}

	@Override
	public Airport findById(long id) {
		
		Optional<Airport> opt = airportRepo.findById(id);
		
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean existsById(long id) {
		
		return airportRepo.existsById(id);
	}

	@Override
	public void deleteById(long id) {
		
		airportRepo.deleteById(id);
	}

	@Override
	public Airport findByAirportCode(String code) {
		
		return airportRepo.findByAirportCode(code);
	}

	@Override
	public List<String> findAllAirportCode() {
		
		return airportRepo.findAllAirportCode();
	}

	@Override
	public List<String> findAllAirportName() {
		
		return airportRepo.findAllAirportName();
	}

}
