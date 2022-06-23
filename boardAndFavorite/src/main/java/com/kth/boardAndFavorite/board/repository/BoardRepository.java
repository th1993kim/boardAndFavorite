package com.kth.boardAndFavorite.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kth.boardAndFavorite.board.domain.Board;
import com.kth.boardAndFavorite.board.dto.BoardResponseDTO;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	@Query(name="getBoardListWithLikesById" ,nativeQuery = true)
	List<BoardResponseDTO> getBoardListWithLikesById(@Param("id")Long id);
}
