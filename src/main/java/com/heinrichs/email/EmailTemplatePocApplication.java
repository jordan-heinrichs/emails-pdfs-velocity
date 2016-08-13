package com.heinrichs.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.heinrichs.email.*")
public class EmailTemplatePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailTemplatePocApplication.class, args);
	}
}
