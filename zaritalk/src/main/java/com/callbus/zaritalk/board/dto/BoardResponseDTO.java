package com.callbus.zaritalk.board.dto;

import com.callbus.zaritalk.customer.domain.AccountType;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardResponseDTO {
	private String title;				//글 제목
	private String content;				//글 내용
	private Long likeCnt;				//좋아요 수
	private String accountId;			//작성자
	private String accountType;			//계정 타입
	private String accountTypeNm;		//계정 타입
	private Boolean likeYn;				//좋아요 등록 여부
	
	public BoardResponseDTO(String title, String content, Long likeCnt, String accountId, String accountType, Boolean likeYn) {
		this.title = title;
		this.content = content;
		this.likeCnt = likeCnt;
		this.accountId = accountId;
		this.accountType = accountType;
		for(AccountType accountTp : AccountType.values()) 
			if(accountTp.name().equalsIgnoreCase(accountType))
				this.accountTypeNm = accountTp.getAuthName();
		this.likeYn = likeYn;
	}
	
	
}
