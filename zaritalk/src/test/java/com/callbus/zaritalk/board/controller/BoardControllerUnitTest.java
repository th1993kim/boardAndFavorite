package com.callbus.zaritalk.board.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.service.BoardLikeService;
import com.callbus.zaritalk.board.service.BoardService;
import com.callbus.zaritalk.customer.domain.AccountType;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
public class BoardControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BoardService boardService;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private BoardLikeService boardLikeService;
	
	
	@Test
	public void insert() throws Exception{
		Customer customer = Customer.builder()
									.id(1L)
									.accountId("th1993kim")
									.accountType(AccountType.REALTOR)
									.nickname("kimtech")
									.quit(false)
									.build();
		Long findId = 1L;
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("test title");
		boardRequestDTO.setContent("testConent");
		
		Board board = Board.builder()
							.id(1L)
							.title("test title")
							.content("test content")
							.customer(customer)
							.delYn(false)
							.regDtm(LocalDateTime.now())
							.build();
		
		
		//given
		given(customerService.findById(findId)).willReturn(customer);
		given(boardService.insert(findId, boardRequestDTO)).willReturn(board);
		
		String content = new ObjectMapper().writeValueAsString(boardRequestDTO);
		
		
		//when
		ResultActions resultAction = mockMvc.perform(post("/boards")
													.contentType(MediaType.APPLICATION_JSON)
													.header("Authorization","REALTOR 1")
													.content(content)
													.accept(MediaType.APPLICATION_JSON)
													);
		
		resultAction
			.andExpect(status().isCreated())
//			.andExpect(jsonPath("$.id").value(1))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
}
