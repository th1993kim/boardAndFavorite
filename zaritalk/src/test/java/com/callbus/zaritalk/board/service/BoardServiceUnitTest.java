package com.callbus.zaritalk.board.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.callbus.zaritalk.board.repository.BoardRepository;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class BoardServiceUnitTest {
	
	@InjectMocks
	private BoardService boardService;
	
	@Mock
	private BoardRepository boardRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
}
