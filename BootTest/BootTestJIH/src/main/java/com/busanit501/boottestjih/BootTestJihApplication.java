package com.busanit501.boottestjih;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BootTestJihApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootTestJihApplication.class, args);
	}

}
