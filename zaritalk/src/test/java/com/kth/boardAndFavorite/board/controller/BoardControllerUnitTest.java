package com.kth.boardAndFavorite.board.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.boardAndFavorite.board.controller.BoardController;
import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.dto.BoardRequestDTO;
import com.kth.boardAndFavorite.board.dto.BoardResponseDTO;
import com.kth.boardAndFavorite.board.service.BoardLikeService;
import com.kth.boardAndFavorite.board.service.BoardService;
import com.kth.boardAndFavorite.customer.domain.AccountType;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(BoardController.class)
public class BoardControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService boardService;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private BoardLikeService boardLikeService;

	private Customer customer;
	private Board board;
	@BeforeEach
	void beforeEach() {
		customer = Customer.builder()
							.id(1L)
							.accountId("th1993kim")
							.accountType(AccountType.REALTOR)
							.nickname("kimtech")
							.quit(false)
							.build();
		
		board = Board.builder()
					.id(1L)
					.title("test save title")
					.content("test save content")
					.customer(customer)
					.build();
	}
	
	@Test
	public void insert() throws Exception{
		Long findId = 1L;
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("test title");
		boardRequestDTO.setContent("testConent");
		
		//given
		given(customerService.getOne(findId)).willReturn(customer); 
		given(boardService.insert(customer, boardRequestDTO)).willReturn(board);
		String content = new ObjectMapper().writeValueAsString(boardRequestDTO);
		
		
		//when
		ResultActions resultAction = mockMvc.perform(post("/boards")
													.contentType(MediaType.APPLICATION_JSON)
													.header("Authorization", "REALTOR 1")
													.content(content)
													.accept(MediaType.APPLICATION_JSON)
													);
		//then
		resultAction
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(1))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	void getList() throws Exception {
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
		ResultActions resultAction = mockMvc.perform(get("/boards")
														.header("Authorization", "REALTOR 1")
														.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void getOne() throws Exception {
		//given
		given(boardService.getOne(1L)).willReturn(board);
		
		//when
		ResultActions resultAction = mockMvc.perform(get("/boards/1")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$.title").value("test save title"))
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void delete() throws Exception {
		//given
		given(customerService.getOne(1L)).willReturn(customer);
		given(boardService.delete(1L,customer)).willReturn(true);
		
		//when
		ResultActions resultAction = mockMvc.perform(MockMvcRequestBuilders.delete("/boards/1")
													.header("Authorization", "REALTOR 1")
													.accept(MediaType.APPLICATION_JSON));
				
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	void update() throws Exception {
		//given
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("fixed title");
		boardRequestDTO.setContent("fixed content");
		given(customerService.getOne(1L)).willReturn(customer);
		given(boardService.update(1L, customer, boardRequestDTO)).willReturn(true);
		String content = new ObjectMapper().writeValueAsString(boardRequestDTO);
		
		//when
		ResultActions resultAction = mockMvc.perform(put("/boards/1")
													.contentType(MediaType.APPLICATION_JSON)
													.header("Authorization","REALTOR 1")
													.content(content)
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void like() throws Exception {
		given(boardService.getOne(1L)).willReturn(board);
		given(customerService.getOne(1L)).willReturn(customer);
		given(boardLikeService.like(board, customer)).willReturn(true);
		//when
		ResultActions resultAction = mockMvc.perform(post("/boards/1/likes")
													.header("Authorization", "REALTOR 1")
													.accept(MediaType.APPLICATION_JSON));
				
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andDo(MockMvcResultHandlers.print());
	}
	
}
