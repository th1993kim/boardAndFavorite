package com.callbus.zaritalk.board.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.domain.BoardLike;
import com.callbus.zaritalk.board.dto.BoardResponseDTO;
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
	@Autowired private BoardLikeRepository boardLikeRepository;
	@Autowired private EntityManager em;
	
	@BeforeEach
	void beforeInit(){
		
		em.createNativeQuery("ALTER TABLE board ALTER COLUMN board_id RESTART WITH 1").executeUpdate();
		em.createNativeQuery("ALTER TABLE customer ALTER COLUMN id RESTART WITH 1").executeUpdate();
		
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
		log.info("new Board = {}",boardEntity);
	}
	
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
								.title("test save title")
								.content("test save content")
								.customer(customer)
								.build();
		//when
		boardRepository.save(boardEntity);
		
		//then
		assertThat(boardEntity.getId()).isNotNull();
	}
	
	@Test
	void findById() {
		//when
		Board board = boardRepository.findById(1L).orElseThrow();
		
		//then
		assertThat(board).isNotNull();
	}
	
	@Test
	void delete() {
		//given
		Board board = boardRepository.findById(1L).orElseThrow();
		
		//when
		boardRepository.delete(board);
		board = boardRepository.findById(1L).orElse(null);
		
		//then
		assertThat(board).isNull();
	}
	
	@Test
	void update() {
		//given
		Board board = boardRepository.findById(1L).orElseThrow();
		
		log.info("update board = {}",board);
		
		//when
		board.update("change title","change content");
		boardRepository.saveAndFlush(board);
		
		//then
		assertThat(board.getTitle()).isEqualTo("change title");
		
	}
	
	
	@Test
	void getBoardListWithLikesById() {
		//given
		Customer customer = customerRepository.findById(1L).orElseThrow();
		Board boardEntity = boardRepository.findById(1L).orElseThrow();
		
		BoardLike boardLike = BoardLike.builder()
										.customer(customer)
										.board(boardEntity)
										.build();
		boardLikeRepository.save(boardLike);
		
		//when
		List<BoardResponseDTO> boardList = boardRepository.getBoardListWithLikesById(1L);
		
		assertThat(boardList).hasSizeGreaterThan(0);
	}
	
	@Test
	void likeFind(){
		//given
		Customer customer = customerRepository.findById(1L).orElseThrow();
		Board boardEntity = boardRepository.findById(1L).orElseThrow();
		BoardLike boardLike = BoardLike.builder()
										.customer(customer)
										.board(boardEntity)
										.build();
		boardLikeRepository.save(boardLike);
		
		//when
		BoardLike boardLikeEntity = boardLikeRepository.findByBoardAndCustomer(boardEntity, customer);
		
		//then
		assertThat(boardLikeEntity).isEqualTo(boardLike);
	}
	
}
