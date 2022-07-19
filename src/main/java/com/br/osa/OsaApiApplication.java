package com.br.osa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class OsaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsaApiApplication.class, args);
	}

}
