package com.epr.eprdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EprApplication {

	public static void main(String[] args) {
		SpringApplication.run(EprApplication.class, args);

		System.out.println("\nServer App Running...\n");
	}

}
