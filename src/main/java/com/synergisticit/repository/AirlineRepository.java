package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

}
