package com.kth.boardAndFavorite.customer.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="customer")
@DynamicInsert
public class Customer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 					//기본키
	
	private String nickname; 			//닉네임
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;	//계정타입( LESSOR : 임대인, REALTOR : 공인 중개사, LESSEE : 임차인 )
	
	private String accountId;			//계정 ID로써 Authentication 값
	
	@ColumnDefault(value="0")
	private Boolean quit;				//탈퇴 여부	
}
