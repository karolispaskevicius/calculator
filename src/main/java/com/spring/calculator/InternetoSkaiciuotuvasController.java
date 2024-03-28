package com.spring.calculator;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Web kontroleris, leidžia viduje naudoti @RequestMapping,
//RestController anotacija nurodo, jog pvz. String tipo rezultatas turėtų būti išspausdinamas klientui toks koks yra
@RestController

//Žymi konfiguracijos komponentą, viduje leidžia kurti Bean su @Bean anotacija
//Ši klases lygio anotacija nurodo spring karkasui "Atspėti" konfiguraciją,
//remiantis priklausomybėmis (Java bibliotekomis) kurios programuotojas užtraukia į projektą
//Šiuo atveju veikia su main metodo
@EnableAutoConfiguration

public class InternetoSkaiciuotuvasController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(){
        //ApplicationContext yra interface skirtas suteikti ijnformacija apie aplikacijos konfiguracija
        //Šiuo atveju naudojama konfiguracija paimama is *.xml failą
        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        //bean klases objektas kuris atitinka singleton šabloną
        HelloWorld bean = (HelloWorld) appContext.getBean("helloWorld");
        return bean.getHello();
      }

}
