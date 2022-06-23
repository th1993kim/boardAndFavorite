package com.kth.boardAndFavorite.board.service;

import org.springframework.stereotype.Service;

import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.domain.BoardLike;
import com.kth.boardAndFavorite.board.repository.BoardLikeRepository;
import com.kth.boardAndFavorite.board.repository.BoardRepository;
import com.kth.boardAndFavorite.customer.domain.Customer;
import com.kth.boardAndFavorite.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardLikeService {

	private final BoardRepository boardRepository;
	private final BoardLikeRepository boardLikeRepository;
	private final CustomerRepository customerRepository;
	
	public Boolean like(Board board, Customer customer) {
		BoardLike boardLike = boardLikeRepository.findByBoardAndCustomer(board,customer);
		if(boardLike != null)
			boardLikeRepository.delete(boardLike);
		else {
			boardLike = BoardLike.builder()
										.board(board)
										.customer(customer)
										.build();
			boardLikeRepository.save(boardLike);
			board.setLikeCnt(board.getLikeCnt()+1);
		}
		return true;
	}
	
}
