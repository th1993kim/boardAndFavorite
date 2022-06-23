package com.kth.boardAndFavorite.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.domain.BoardLike;
import com.kth.boardAndFavorite.customer.domain.Customer;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
	
	public BoardLike findByBoardAndCustomer(Board board, Customer customer);

}
