package com.lucky.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LuckyEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckyEurekaApplication.class, args);
	}

}
