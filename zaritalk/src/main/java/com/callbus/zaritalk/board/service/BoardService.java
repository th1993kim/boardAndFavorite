package com.callbus.zaritalk.board.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.dto.BoardResponseDTO;
import com.callbus.zaritalk.board.repository.BoardRepository;
import com.callbus.zaritalk.common.exception.AuthenticationException;
import com.callbus.zaritalk.customer.domain.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	
	public List<BoardResponseDTO> getList(Long id){
		return boardRepository.getBoardListWithLikesById(id);
	}
	
	public Board getOne(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));
	}
	
	public Board insert(Customer customer,BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Board board = Board.builder()
							.title(boardRequestDTO.getTitle())
							.content(boardRequestDTO.getContent())
							.customer(customer)
	   						.build();
		return boardRepository.save(board);
	}



	public Boolean delete(Long boardId, Customer customer) throws AuthenticationException {
		Board board = idEqualsCheck(boardId, customer.getId());
		boardRepository.delete(board);
		return true;
	}

	public Boolean update(Long boardId,Customer customer, BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Board board = idEqualsCheck(boardId, customer.getId());
		board.update(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
		return true;
	}
	
	// 수정 / 삭제 시 작성자와 요청이들어온 ID가 동일한지 체크하는 메서드
	// @Return : BoardEntity
	private Board idEqualsCheck(Long boardId, Long id) throws AuthenticationException {
		Board board = this.getOne(boardId);
		if(board.getCustomer().getId() != id) 
			throw new AuthenticationException();
		return board;
	}
}
