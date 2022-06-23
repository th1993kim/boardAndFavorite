package com.kth.boardAndFavorite.board.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.boardAndFavorite.board.dto.BoardRequestDTO;
import com.kth.boardAndFavorite.customer.domain.AccountType;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BoardControllerIntegreTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void insert() throws Exception{
		
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		boardRequestDTO.setTitle("test title");
		boardRequestDTO.setContent("testConent");
		
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
			.andExpect(jsonPath("$.id").value(31L))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getOne() throws Exception{
		//when
		ResultActions resultAction = mockMvc.perform(get("/boards/1")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$.likeCnt").value(0))
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void getList() throws Exception{
		//when
		ResultActions resultAction = mockMvc.perform(get("/boards")
													.header("Authorization", "REALTOR 1")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$.boardList[0].accountType").value(AccountType.REALTOR.name()))
					.andExpect(jsonPath("$.boardList[0].likeYn").value("true"))
					.andDo(MockMvcResultHandlers.print());
	}

	
	@Test
	public void drop() throws Exception{
		//when
		ResultActions resultAction = mockMvc.perform(delete("/boards/1")
													.header("Authorization", "REALTOR 1")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void update() throws Exception{
		//given
		BoardRequestDTO boardRequestDTO = new BoardRequestDTO();
		
		boardRequestDTO.setTitle("fixed Data");
		boardRequestDTO.setContent("fixed Content");
		
		String content = new ObjectMapper().writeValueAsString(boardRequestDTO);
		
		//when
		ResultActions resultAction = mockMvc.perform(put("/boards/1")
													.contentType(MediaType.APPLICATION_JSON)
													.content(content)
													.header("Authorization", "REALTOR 1")
													.accept(MediaType.APPLICATION_JSON));
		
		//then
		resultAction.andExpect(status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void like() throws Exception{
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
