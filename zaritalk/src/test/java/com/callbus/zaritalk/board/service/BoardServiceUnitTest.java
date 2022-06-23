package com.callbus.zaritalk.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.domain.BoardLike;
import com.kth.boardAndFavorite.board.dto.BoardRequestDTO;
import com.kth.boardAndFavorite.board.dto.BoardResponseDTO;
import com.kth.boardAndFavorite.board.repository.BoardLikeRepository;
import com.kth.boardAndFavorite.board.repository.BoardRepository;
import com.kth.boardAndFavorite.board.service.BoardLikeService;
import com.kth.boardAndFavorite.board.service.BoardService;
import com.kth.boardAndFavorite.common.exception.AuthenticationException;
import com.kth.boardAndFavorite.customer.domain.AccountType;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BoardServiceUnitTest {
	
	@InjectMocks
	private BoardService boardService;
	
	@InjectMocks
	private BoardLikeService boardLikeService;
	
	@Mock
	private BoardRepository boardRepository;
	
	@Mock
	private CustomerRepository customerRepository;	
	
	@Mock
	private BoardLikeRepository boardLikeRepository;
	
	private Board boardEntity;
	private Customer customer;
	
	@BeforeEach
	void beforEach() {
		customer = Customer.builder()
				.id(1L)
				.accountId("th1993kim")
				.accountType(AccountType.REALTOR)
				.nickname("kimtech")
				.quit(false)
				.build();
		
		boardEntity = Board.builder()
							.id(1L)
							.title("test save title")
							.content("test save content")
							.customer(customer)
							.build();
	}
	
	@Test
	public void insert() throws AuthenticationException {
		
		//ready
		Long id= 1L;
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("test title");
		boardRequestDTO.setContent("testConent");
		
		//given
		given(boardService.insert(customer, boardRequestDTO)).willReturn(boardEntity);
		
		//when
		Board board = boardService.insert(customer, boardRequestDTO);
		
		//then
		assertEquals(boardEntity, board);
	}
	
	@Test
	void getList() {
		
		//given
		List<BoardResponseDTO> boardList = new ArrayList<>();
		BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
		boardResponseDTO.setAccountId("kth");
		boardResponseDTO.setAccountType(AccountType.REALTOR.name());
		boardResponseDTO.setAccountTypeNm(AccountType.REALTOR.getAuthName());
		boardResponseDTO.setTitle("test data");
		boardResponseDTO.setContent("test content");
		boardResponseDTO.setLikeCnt(0L);
		boardResponseDTO.setLikeYn(false);
		boardList.add(boardResponseDTO);
		
		given(boardService.getList(1L)).willReturn(boardList);
		
		//when
		List<BoardResponseDTO> resultList = boardService.getList(1L);
		
		//then
		assertThat(resultList).hasSizeGreaterThan(0);
	}
	
	@Test
	void getOne() {
		//given
		Optional<Board> boardOp = Optional.of(boardEntity);
		given(boardRepository.findById(1L)).willReturn(boardOp);
		
		//when
		Board board = boardService.getOne(1L);
		
		//then
		assertThat(board).isEqualTo(boardEntity);
		
	}
	
	@Test
	void delete() throws AuthenticationException {
		
		//given
		Optional<Board> boardOp = Optional.of(boardEntity);
		given(boardRepository.findById(1L)).willReturn(boardOp);
		
		//when
		Boolean delete = boardService.delete(1L,customer);
		
		//then
		assertThat(delete).isTrue();
		
	}
	
	@Test
	void update() throws AuthenticationException {
		//given
		Optional<Board> boardOp = Optional.of(boardEntity);
		given(boardRepository.findById(1L)).willReturn(boardOp);
		
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("title renewal");
		boardRequestDTO.setContent("content renewal");
		
		//when
		Boolean update = boardService.update(1L, customer, boardRequestDTO);
		
		//then
		assertThat(update).isTrue();
	}
	
	@Test
	void like() throws Exception{
		//given
		Customer customerEntity = Customer.builder()
											.id(1L)
											.accountId("th1993kim")
											.nickname("kth")
											.accountType(AccountType.REALTOR)
											.quit(false)
											.build();
		BoardLike boardLike = BoardLike.builder()
										.boardLikeId(1L)
										.board(boardEntity)
										.customer(customerEntity)
										.build();
		
		given(boardLikeRepository.findByBoardAndCustomer(boardEntity, customerEntity)).willReturn(boardLike);
		
		//when
		Boolean result = boardLikeService.like(boardEntity,customerEntity);
		
		//then
		assertThat(result).isTrue();
		
	}
	
	
	
	
	
	
}
