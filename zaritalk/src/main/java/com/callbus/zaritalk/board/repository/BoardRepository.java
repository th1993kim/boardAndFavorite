package com.callbus.zaritalk.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.dto.BoardResponseDTO;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Query(name="getBoardListWithLikesById" ,nativeQuery = true)
	List<BoardResponseDTO> getBoardListWithLikesById(@Param("id")Long id);
}
