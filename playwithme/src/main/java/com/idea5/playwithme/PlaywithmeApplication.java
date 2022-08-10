package com.idea5.playwithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) -> 데이터소스 설정 클래스를 자동설정에서 제외시켜서 문제 발생
@SpringBootApplication
public class PlaywithmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaywithmeApplication.class, args);
	}

}




