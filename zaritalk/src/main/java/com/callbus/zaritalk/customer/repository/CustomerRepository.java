package com.callbus.zaritalk.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callbus.zaritalk.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByAccountId(String accountId);

}
