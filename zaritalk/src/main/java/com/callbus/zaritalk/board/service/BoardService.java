package com.callbus.zaritalk.board.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardRequestDTO;
import com.callbus.zaritalk.board.dto.BoardResponseDTO;
import com.callbus.zaritalk.board.exception.AuthenticationException;
import com.callbus.zaritalk.board.repository.BoardRepository;
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
	//private final EntityManager em;
	
	public List<BoardResponseDTO> getList(BoardRequestDTO boardRequestDTO){
		//ArrayList<BoardResponseDTO> boardList = boardRepository.findAllList(boardRequestDTO);
		StringBuilder query = new StringBuilder();
		query.append("SELECT b.title,b.content,b.like_cnt,c.account_id,c.account_type,bl.like_yn FROM board b")
			 .append("LEFT JOIN customer c ON b.id = c.id")
			 .append("LEFT JOIN (SELECT (COUNT(*)>0) as like_yn, board_id FROM board_like WHERE id = 1 GROUP BY board_id) bl ON b.board_id = bl.board_id");
		
//		List<BoardResponseDTO> boardReseponseDTOList = em.createQuery("SELECT new com.callbus.zaritalk.board.dto.BoardResponseDTO(b.title,b.content,b.likeCnt,c.accountId,c.accountType,true) "
//																	+ "FROM Board b "
//																	+ "LEFT JOIN b.customer c "
//																	+ "LEFT JOIN b.boardLikeList GROUP BY boardId bl ON bl.id = :id"
//																	,BoardResponseDTO.class)
//														 .setParameter("id", boardRequestDTO.getId())
//														 .getResultList();
//		
		return boardRepository.getBoardListWithLikesById(boardRequestDTO.getId());
	}
	
	public Board getOne(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow();
	}
	
	public Board insert(BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Customer customer = customerRepository.findById(boardRequestDTO.getId()).orElseThrow();
		if(customer == null)
			throw new AuthenticationException();
		Board board = Board.builder()
										.title(boardRequestDTO.getTitle())
										.content(boardRequestDTO.getContent())
										.customer(customer)
				   						.build();
		return boardRepository.save(board);
	}



	public Boolean delete(Long boardId, BoardRequestDTO boardRequestDTO) throws AuthenticationException {
		Board board = idEqualsCheck(boardId, boardRequestDTO);
		boardRepository.delete(board);
		return true;
	}

	public Boolean update(Long boardId, BoardRequestDTO boardRequestDTO) throws AuthenticationException{
		Board board = idEqualsCheck(boardId, boardRequestDTO);
		board.update(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
		return true;
	}
	
	// 수정 / 삭제 시 작성자와 요청이들어온 ID가 동일한지 체크하는 메서드
	// @Return : BoardEntity
	private Board idEqualsCheck(Long boardId, BoardRequestDTO boardRequestDTO) throws AuthenticationException {
		Board board = boardRepository.findById(boardId).orElseThrow();
		if(board.getCustomer().getId() != (boardRequestDTO.getId())) 
			throw new AuthenticationException();
		return board;
	}
}
