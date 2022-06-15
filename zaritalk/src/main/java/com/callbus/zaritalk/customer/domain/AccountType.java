package com.callbus.zaritalk.customer.domain;

public enum AccountType {
	REALTOR("공인중개사"),
	LESSOR("임대인"),
	LESSEE("임차인");

	
	private String authName;
	
	private AccountType() {
		
	}
	
	private AccountType(String authName) {
		this.authName = authName;
	}
	
	public String getAuthName() {
		return this.authName;
	}

}
