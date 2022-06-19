package com.callbus.zaritalk.board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.customer.domain.AccountType;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Slf4j
public class BoardRepositoryUnitTest {

	@Autowired private BoardRepository boardRepository;
	@Autowired private CustomerRepository customerRepository;
	
	@Test
	void save() {
		//given
		Customer customer = Customer.builder()
									.accountId("th1993kim")
									.nickname("kth")
									.accountType(AccountType.REALTOR)
									.quit(false)
									.build();
		customerRepository.save(customer);
		Board boardEntity = Board.builder()
								.title("test title")
								.content("test content")
								.customer(customer)
								.build();
		boardRepository.save(boardEntity);
		assertThat(boardEntity.getId()).isNotNull();
	}
	
}
