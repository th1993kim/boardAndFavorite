package com.callbus.zaritalk.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.dto.BoardResponseDTO;
import com.callbus.zaritalk.board.repository.BoardRepository;
import com.callbus.zaritalk.common.exception.AuthenticationException;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	private final CustomerRepository customerRepository;
	
	public List<BoardResponseDTO> getList(Long id){
		return boardRepository.getBoardListWithLikesById(id);
	}
	
	public Board getOne(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow();
	}
	
	public Board insert(Long id,BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Board board = Board.builder()
							.title(boardRequestDTO.getTitle())
							.content(boardRequestDTO.getContent())
							.customer(Customer.builder().id(id).build())
	   						.build();
		return boardRepository.save(board);
	}



	public Boolean delete(Long boardId, Long id) throws AuthenticationException {
		Board board = idEqualsCheck(boardId, id);
		boardRepository.delete(board);
		return true;
	}

	public Boolean update(Long boardId,Long id, BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Board board = idEqualsCheck(boardId, id);
		board.update(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
		return true;
	}
	
	// 수정 / 삭제 시 작성자와 요청이들어온 ID가 동일한지 체크하는 메서드
	// @Return : BoardEntity
	private Board idEqualsCheck(Long boardId, Long id) throws AuthenticationException {
		Board board = boardRepository.findById(boardId).orElseThrow();
		if(board.getCustomer().getId() != id) 
			throw new AuthenticationException();
		return board;
	}
}
