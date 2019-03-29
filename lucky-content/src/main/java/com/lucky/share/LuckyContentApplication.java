package com.lucky.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableTransactionManagement
public class LuckyContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckyContentApplication.class, args);
	}

}
