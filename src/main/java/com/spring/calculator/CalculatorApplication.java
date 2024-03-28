package com.spring.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication anotacija yra lygi @Configuration, EnableAutoConfiguration ir @Component Scan
//Nurodoma klasėje, turinčioje pagrindinį (main) metodą.

@SpringBootApplication
//Web kontroleris. Pažymi MVC valdiklį. Leidžia viduje naudoti @RequestMapping
//@RestController anotacija nurodo, jog string tipo rezultatas turėtų būti išspausdinamas toks koks yra
@RestController
public class CalculatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
		System.out.println("Pirmoji spring boot aplikacija");
	}
	@GetMapping
	public String hello(@RequestParam(value= "name", defaultValue= "World") String name){
		return String.format("Hello %s!", name);
	}
}
