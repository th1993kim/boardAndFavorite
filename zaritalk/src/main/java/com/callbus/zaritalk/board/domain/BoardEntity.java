package com.callbus.zaritalk.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.callbus.zaritalk.customer.domain.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="board")
public class BoardEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardId;			//글번호
	@NonNull
	private String title;			//제목
	private String content;			//내용
	private Boolean delYn;			//삭제여부
	@ManyToOne
	@JoinColumn(name="id")
	private CustomerEntity customer;	//작성자
	@CreatedDate
	private LocalDateTime regDtm;	//등록일
	@LastModifiedDate
	private LocalDateTime updDtm;	//수정일
	private LocalDateTime delDtm;	//삭제일
	
}
