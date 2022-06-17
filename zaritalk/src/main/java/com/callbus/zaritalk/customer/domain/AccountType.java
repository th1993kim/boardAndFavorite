package com.callbus.zaritalk.customer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccountType {
	REALTOR("공인중개사"),
	LESSOR("임대인"),
	LESSEE("임차인");

	
	@Getter
	private String authName;
	
	AccountType(String authName) {
		this.authName = authName;
	}
}
