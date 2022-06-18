package com.callbus.zaritalk.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.callbus.zaritalk.board.domain.Board;
import com.callbus.zaritalk.board.domain.BoardLike;
import com.callbus.zaritalk.board.repository.BoardLikeRepository;
import com.callbus.zaritalk.board.repository.BoardRepository;
import com.callbus.zaritalk.customer.domain.AccountType;
import com.callbus.zaritalk.customer.domain.Customer;
import com.callbus.zaritalk.customer.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InitService {
	
	private final CustomerRepository customerRepository;
	private final BoardRepository boardRepository;
	private final BoardLikeRepository boardLikeRepository;
	
	@PostConstruct
	public void initialize() {
		List<Customer> customerList = new ArrayList<>();
		Customer realtor = Customer.builder().accountId("Realtor").nickname("공인중").accountType(AccountType.REALTOR).build();
		Customer lessor = Customer.builder().accountId("Lessor").nickname("임대").accountType(AccountType.LESSOR).build();
		Customer lessee = Customer.builder().accountId("Lessee").nickname("임차").accountType(AccountType.LESSEE).build();
		customerList.add(realtor);
		customerList.add(lessor);
		customerList.add(lessee);
		customerRepository.saveAllAndFlush(customerList);
		
		List<Board> boardList = new ArrayList<>();
		for(int i=0; i<10; i++) {
			boardList.add(Board.builder().customer(realtor).title("realtor 테스트 " + i + "번째").content("내용은 가짜").build());
			boardList.add(Board.builder().customer(lessor).title("lessor 테스트 " + i + "번째").content("내용은 가짜").build());
			boardList.add(Board.builder().customer(lessee).title("lessee 테스트 " + i + "번째").content("내용은 가짜").build());
		}
		boardRepository.saveAllAndFlush(boardList);
		
		List<BoardLike> boardLikeList = new ArrayList<>();
		for(int i=0; i<boardList.size(); i++)
			boardLikeList.add(BoardLike.builder().customer(realtor).board(boardList.get(i)).build());
		boardLikeList.add(BoardLike.builder().customer(lessor).board(boardList.get(0)).build());
		boardLikeList.add(BoardLike.builder().customer(lessee).board(boardList.get(0)).build());
		
		boardLikeRepository.saveAll(boardLikeList);
	}
	
}
