package com.callbus.zaritalk.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.board.BoardRepository;
import com.callbus.zaritalk.board.domain.BoardEntity;
import com.callbus.zaritalk.board.dto.InsertBoardDTO;
import com.callbus.zaritalk.customer.domain.CustomerEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	private final BoardRepository boardRespository;
	
	public List<BoardEntity> getBoardList(){
		return boardRespository.findAll();
	}

	public BoardEntity insertBoard(InsertBoardDTO insertBoardDTO) {
		BoardEntity board = BoardEntity.builder()
										.title(insertBoardDTO.getTitle())
										.content(insertBoardDTO.getContent())
										.customer(CustomerEntity.builder().accountId(insertBoardDTO.getAccountId()).build())
				   						.build();
		return boardRespository.save(board);
	}

	public BoardEntity getBoard(Long boardId) {
		return boardRespository.findById(boardId).orElseThrow();
	}
}
