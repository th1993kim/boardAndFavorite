package com.kth.boardAndFavorite.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResult {
	private Integer code;
	private String message;
}
