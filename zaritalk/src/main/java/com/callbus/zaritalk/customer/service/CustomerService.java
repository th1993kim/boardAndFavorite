package com.callbus.zaritalk.customer.service;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}
	
}
