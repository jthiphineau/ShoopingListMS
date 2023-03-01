package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"dao", "entities", "web"})
public class ShoppingListMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingListMsApplication.class, args);
	}

	CommandLineRunner start() {
		return args -> {
		};
	}
}
