package com.callbus.zaritalk.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.service.BoardLikeService;
import com.callbus.zaritalk.board.service.BoardService;
import com.callbus.zaritalk.common.annotation.AuthCheck;
import com.callbus.zaritalk.common.annotation.Id;
import com.callbus.zaritalk.common.exception.AuthenticationException;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class boardController {
	
	private final BoardService boardService;
	private final BoardLikeService boardLikeService;
	private final CustomerService customerService;
	
	/*
	 * @Return : List<BoardEntity>
	 */
	@GetMapping("/boards")
	public ResponseEntity<?> getList(@Id Long id) throws Exception{
		return new ResponseEntity<>(boardService.getList(id), HttpStatus.OK);
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
	public ResponseEntity<?> insert(@Id Long id,BoardRequestDTO boardRequestDTO) throws Exception{
		Customer customer = customerService.findById(id);
		if(customer == null)
			throw new AuthenticationException();
		return new ResponseEntity<>(boardService.insert(id,boardRequestDTO),HttpStatus.CREATED);
	}
	
	/*
	 * @Header : Authorization
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<?> delete(@PathVariable("boardId") Long boardId, @Id Long id) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(boardService.delete(boardId,id));
	}
	
	/*
	 * @Header : Authorization
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@PutMapping("/boards/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") Long boardId, @Id Long id ,BoardRequestDTO boardRequestDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(boardService.update(boardId,id,boardRequestDTO));
	}
	
	/*
	 * @Header : Authorization
	 * @Param : BoardLikeRequestDTO
	 * @Return : Boolean
	 */
	@AuthCheck
	@PostMapping("/boards/{boardId}/likes")
	public ResponseEntity<?> like(@PathVariable("boardId") Long boardId, @Id Long id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(boardLikeService.like(boardId,id));
	}
	
	
}
