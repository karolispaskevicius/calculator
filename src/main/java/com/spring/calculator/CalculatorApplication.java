package com.spring.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication anotacija yra lygi @Configuration, @EnableAutoConfiguration ir @ComponentScan
// Nurodoma klasėje, turinčioje pagrindinį (main) metodą.
@SpringBootApplication
public class CalculatorApplication {
	public static void main(String[] args) {
		// Programos kontrolės deleguojama statiniam klasės SpringApplication metodui run
		// nurodant aplikacijos šakninį komponentą. Spring karkasas paleis aplikaciją,
		// t.y startuos serverį su numatytaisiais parametrais
		SpringApplication.run(CalculatorApplication.class, args);
		System.out.println("Woohoo pirmoji spring boot aplikacija");
	}
}
