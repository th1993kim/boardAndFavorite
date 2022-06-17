package com.callbus.zaritalk.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.domain.BoardLike;
import com.callbus.zaritalk.customer.domain.Customer;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
	
	public BoardLike findByBoardAndCustomer(Board board, Customer customer);

}
