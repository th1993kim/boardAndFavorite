package com.kth.boardAndFavorite.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.boardAndFavorite.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByAccountId(String accountId);

}
