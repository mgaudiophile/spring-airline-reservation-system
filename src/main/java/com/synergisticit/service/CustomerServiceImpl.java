package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public Customer save(Customer customer) {
		
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		
		return customerRepo.findAll();
	}

	@Override
	public Customer findById(long id) {
		
		Optional<Customer> opt = customerRepo.findById(id);
		
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public void deleteById(long id) {
		
		customerRepo.deleteById(id);
	}

}
