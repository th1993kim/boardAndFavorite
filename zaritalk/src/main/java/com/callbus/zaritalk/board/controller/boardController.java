package com.callbus.zaritalk.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.callbus.zaritalk.board.aop.AuthCheck;
import com.callbus.zaritalk.board.dto.BoardLikeRequestDTO;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.service.BoardLikeService;
import com.callbus.zaritalk.board.service.BoardService;
import com.callbus.zaritalk.customer.domain.AccountType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class boardController {
	
	private final BoardService boardService;
	private final BoardLikeService boardLikeService;
	
	/*
	 * @Param  : BoardRequestDTO
	 * @Return : List<BoardEntity>
	 */
	@GetMapping("/boards")
	public ResponseEntity<?> getList(@RequestBody BoardRequestDTO boardRequestDTO) throws Exception{
		log.info("accountType : " +AccountType.LESSEE.getAuthName());
		return new ResponseEntity<>(boardService.getList(boardRequestDTO), HttpStatus.OK);
	}
	
	/*
	 * @Return : BoardEntity
	 */
	@GetMapping("/boards/{boardId}")
	public ResponseEntity<?> getOne(@PathVariable("boardId") Long boardId) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(boardService.getOne(boardId));
	}
	
	/*
	 * @Param  : BoardRequestDTO
	 * @Return : BoardEntity
	 */
	@AuthCheck
	@PostMapping("/boards")
	public ResponseEntity<?> insert(@RequestBody BoardRequestDTO boardRequestDTO) throws Exception{
		return new ResponseEntity<>(boardService.insert(boardRequestDTO),HttpStatus.CREATED);
	}
	
	/*
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<?> delete(@PathVariable("boardId") Long boardId, @RequestBody BoardRequestDTO boardRequestDTO) throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(boardService.delete(boardId,boardRequestDTO));
	}
	
	/*
	 * @Param : BoardRequestDTO
	 * @Return : Boolean 
	 */
	@AuthCheck
	@PutMapping("/boards/{boardId}")
	public ResponseEntity<?> update(@PathVariable("boardId") Long boardId, @RequestBody BoardRequestDTO boardRequestDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(boardService.update(boardId,boardRequestDTO));
	}
	
	/*
	 * @Param : BoardLikeRequestDTO
	 * @Return : Boolean
	 */
	@AuthCheck
	@PostMapping("/boards/{boardId}/likes")
	public ResponseEntity<?> like(@PathVariable("boardId") Long boardId, @RequestBody BoardLikeRequestDTO boardLikeRequestDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(boardLikeService.like(boardId,boardLikeRequestDTO));
	}
	
	
}
