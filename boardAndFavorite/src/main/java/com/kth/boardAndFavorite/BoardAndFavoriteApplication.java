package com.kth.boardAndFavorite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //AOP 사용
@SpringBootApplication
public class BoardAndFavoriteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardAndFavoriteApplication.class, args);
	} 

}
