package com.kth.boardAndFavorite.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.dto.BoardRequestDTO;
import com.kth.boardAndFavorite.board.service.BoardLikeService;
import com.kth.boardAndFavorite.board.service.BoardService;
import com.kth.boardAndFavorite.common.annotation.AuthCheck;
import com.kth.boardAndFavorite.common.annotation.GetCustomer;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	private final BoardLikeService boardLikeService;
	private final CustomerService customerService;
	
	/*
	 * @Return : List<BoardEntity>
	 */
	@GetMapping("/boards")
	public ResponseEntity<?> getList(@GetCustomer Customer customer) throws Exception{
		Map<String,Object> boardList = new HashMap<String,Object>();
		Long id = null;
		if(customer != null)
			id = customer.getId();
		boardList.put("boardList", boardService.getList(id));
		return ResponseEntity.status(HttpStatus.OK).body(boardList);
	}
		
	/*
	 * @Return : BoardEntity
	 */
	@GetMapping("/boards/{boardId}")
	public ResponseEntity<?> getOne(@PathVariable("boardId") Long boardId) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(boardService.getOne(boardId));
	}
	
	/*
	 * @Header : Authorization
	 * @Param  : BoardRequestDTO
	 * @Return : BoardEntity
	 */
	@AuthCheck
	@PostMapping("/boards")
	public ResponseEntity<?> insert(@GetCustomer Customer customer, @RequestBody BoardRequestDTO boardRequestDTO) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(boardService.insert(customer,boardRequestDTO));
	} 
	
	/*
	 * @Header : Authorization
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<?> delete(@PathVariable("boardId") Long boardId, @GetCustomer Customer customer) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(boardService.delete(boardId,customer));
	}
	
	/*
	 * @Header : Authorization
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@PutMapping("/boards/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") Long boardId, @GetCustomer Customer customer ,@RequestBody BoardRequestDTO boardRequestDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(boardService.update(boardId,customer,boardRequestDTO));
	}
	
	/*
	 * @Header : Authorization
	 * @Param : BoardLikeRequestDTO
	 * @Return : Boolean
	 */
	@AuthCheck
	@PostMapping("/boards/{boardId}/likes")
	public ResponseEntity<?> like(@PathVariable("boardId") Long boardId, @GetCustomer Customer customer) throws Exception {
		Board board = boardService.getOne(boardId);
		return ResponseEntity.status(HttpStatus.OK).body(boardLikeService.like(board,customer));
	}
	
	
}
