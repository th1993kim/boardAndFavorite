package com.callbus.zaritalk.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardRequestDTO {
	@NotBlank
	private String title;		//글제목
	private String content;		//글내용
}
