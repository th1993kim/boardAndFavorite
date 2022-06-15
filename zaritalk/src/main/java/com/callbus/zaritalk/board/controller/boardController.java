package com.callbus.zaritalk.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.callbus.zaritalk.board.aop.AuthCheck;
import com.callbus.zaritalk.board.dto.InsertBoardDTO;
import com.callbus.zaritalk.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController("/boards")
@RequiredArgsConstructor
public class boardController {
	
	private final BoardService boardService;
	
	@AuthCheck
	@GetMapping("")
	public ResponseEntity<?> getBoardList() throws Exception{
		return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
	}
	
	@AuthCheck
	@PostMapping("")
	public ResponseEntity<?> insertBoard(@RequestBody InsertBoardDTO insertBoardDTO , HttpServletRequest request) throws Exception{
		return new ResponseEntity<>(boardService.insertBoard(insertBoardDTO),HttpStatus.CREATED);
	}
	
	@AuthCheck
	@GetMapping("/{boardId}")
	public ResponseEntity<?> getBoard(@PathVariable("boardId") Long boardId) throws Exception{
		return new ResponseEntity<>(boardService.getBoard(boardId),HttpStatus.OK);
	}
}
