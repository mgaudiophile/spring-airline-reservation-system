package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "SELECT * FROM ticket t WHERE t.customerId = :customerId", nativeQuery = true)
	List<Ticket> findAllByCustomerId(long customerId);
}
