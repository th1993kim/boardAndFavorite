package com.kth.boardAndFavorite.board.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kth.boardAndFavorite.board.dto.BoardResponseDTO;
import com.kth.boardAndFavorite.customer.domain.AccountType;
import com.kth.boardAndFavorite.customer.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NamedNativeQuery(name="getBoardListWithLikesById",
				  query = "SELECT b.title,b.content,b.like_cnt,c.account_id,c.account_type,NVL2(bl.like_yn,bl.like_yn,FALSE) as like_yn "
				  		+ "FROM board b "
				  		+ "LEFT JOIN customer c ON b.id = c.id "
				  		+ "LEFT JOIN (SELECT count(*)>0 AS like_yn, board_id FROM board_like WHERE id = :id GROUP BY board_id) bl ON b.board_id = bl.board_id",
				  resultSetMapping = "boardResponseDTO")
@SqlResultSetMapping(name="boardResponseDTO",
					classes = @ConstructorResult(targetClass = BoardResponseDTO.class,
												 columns = {@ColumnResult(name = "title", 	 	 type=String.class),
														 	@ColumnResult(name = "content", 	 type=String.class),
														 	@ColumnResult(name = "like_cnt", 	 type=Long.class),
														 	@ColumnResult(name = "account_id",   type=String.class),
														 	@ColumnResult(name = "account_type", type=String.class),
														 	@ColumnResult(name = "like_yn", 	 type=Boolean.class)
															}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="board")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;			//?????????
	
	private String title;			//??????
	
	private String content;			//??????
	
	@ColumnDefault(value = "false")
	private Boolean delYn;			//????????????
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id")
	private Customer customer;	//?????????
	
	@OneToMany(mappedBy = "board")
	private List<BoardLike> boardLikeList;
	
	@ColumnDefault(value = "0")
	private Long likeCnt;			// ????????? ??????
	
	@CreatedDate
	private LocalDateTime regDtm;	//?????????
	
	@LastModifiedDate
	private LocalDateTime updDtm;	//?????????
	
	private LocalDateTime delDtm;	//?????????
	
	//????????? ????????? ?????????
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	//????????? ??????????????? likeCnt update???
	public void setLikeCnt(Long likeCnt) {
		this.likeCnt = likeCnt;
	}
}
