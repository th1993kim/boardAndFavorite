package com.callbus.zaritalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //AOP 사용
@SpringBootApplication
public class ZaritalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaritalkApplication.class, args);
	} 

}
