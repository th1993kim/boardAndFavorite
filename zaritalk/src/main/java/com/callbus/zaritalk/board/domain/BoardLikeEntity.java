package com.callbus.zaritalk.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.callbus.zaritalk.customer.domain.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="board_like")
public class BoardLikeEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardLikeId;
	@ManyToOne
	@JoinColumn(name="boardId")
	private BoardEntity boardEntity;
	@ManyToOne
	@JoinColumn(name="id")
	private CustomerEntity customerEntity;
}
