package com.kth.boardAndFavorite.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kth.boardAndFavorite.customer.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="board_like")
@EntityListeners(AuditingEntityListener.class)
public class BoardLike {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_Like_id")
	private Long boardLikeId;			//좋아요 번호
	
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;			//해당 게시판
	
	@ManyToOne
	@JoinColumn(name="id")
	private Customer customer;	//해당 고객
	
	@CreatedDate
	private LocalDateTime regDtm;		//좋아요 등록일
}
