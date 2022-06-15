package com.callbus.zaritalk.board;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callbus.zaritalk.board.domain.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
