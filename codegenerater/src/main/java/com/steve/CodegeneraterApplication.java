package com.steve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.steve.mapper")
public class CodegeneraterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodegeneraterApplication.class, args);
	}

}
