package com.worldline.springcloudarchetype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class BookMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMSApplication.class, args);
	}

}
