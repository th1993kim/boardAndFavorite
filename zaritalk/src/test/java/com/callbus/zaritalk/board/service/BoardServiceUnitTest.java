package com.callbus.zaritalk.board.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.repository.BoardRepository;
import com.callbus.zaritalk.common.exception.AuthenticationException;
import com.callbus.zaritalk.customer.domain.AccountType;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BoardServiceUnitTest {
	
	@InjectMocks
	private BoardService boardService;
	
	@Mock
	private BoardRepository boardRepository;
	
	@Mock
	private CustomerRepository customerRepository;	
	
	@Test
	public void insert() throws AuthenticationException {
		
		//ready
		Board boardEntity = Board.builder()
							.id(1L)
							.title("test title")
							.content("test content")
							.customer(Customer.builder()
									.id(1L)
									.accountId("kth1993")
									.nickname("김태현")
									.accountType(AccountType.REALTOR)
									.build())
							.delYn(false)
							.regDtm(LocalDateTime.now())
							.build();
		
		Long id= 1L;
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("test title");
		boardRequestDTO.setContent("testConent");
		
		//given
		given(boardService.insert(id, boardRequestDTO)).willReturn(boardEntity);
		
		//when
		Board board = boardService.insert(id, boardRequestDTO);
		
		//then
		assertEquals(boardEntity, board);
	}
	
}
