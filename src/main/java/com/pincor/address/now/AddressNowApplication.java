package com.pincor.address.now;

import com.pincor.address.now.controller.UserController;
import com.pincor.address.now.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AddressNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressNowApplication.class, args);
	}

}
