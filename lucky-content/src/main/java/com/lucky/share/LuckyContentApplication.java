package com.lucky.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LuckyContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckyContentApplication.class, args);
	}

}
