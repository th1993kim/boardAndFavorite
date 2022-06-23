package com.kth.boardAndFavorite.customer.service;

import org.springframework.stereotype.Service;

import com.kth.boardAndFavorite.common.exception.AuthenticationException;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	public Customer getOne(Long id) throws AuthenticationException {
		return customerRepository.findById(id).orElseThrow(() -> new AuthenticationException());
	}
	
}
