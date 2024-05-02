package com.spring.calculator.controller;

import com.spring.calculator.service.NumberService;
import com.spring.calculator.model.Number;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

// @RestController anotacija nurodo, jog String tipo rezultatas turėtų būti išspausdinamas klientui toks koks yra.
// @RestController negrąžina view.
//kadangi mums reikia grąžinti view pagal Spring MVC, naudojame @Controller
@Controller
// @EnableAutoConfiguration - žymi konfigūracijos komponentą. Viduje leidžia kurti bean metodus su @bEAN
// remiantis priklausomybėmis (jar bibliotekos), kurias programuotojas įtraukė į projektą.
// šiuo atveju ji veikia kartu su main metodu
@EnableAutoConfiguration
public class CalculatorController {
    // autowire- naudojamas automatinei priklausomybių injekcijai
    // kad panaudoti @Autowired anotaciją, reikia pirmiausiai turėti apsirašius @Bean @Configuration klasėje
    @Autowired
    // @Qualifier anotacija kartu su @Autowired patikslina su kuriuo konkrečiai bean susieti priklausomybę.
    // Jeigu @Configuration klasėje yra daugiau negu vienas bean, @Qualifier anotacija yra privaloma,
    // kitu atveju metama klaida:
    // 'Consider making one of the beans as @Primary, updating the consumer to accept multiple beans,
    // or using @Qualifier to identify the bean that should be consumed'
    @Qualifier("NumberService")
    public NumberService numberService;
    // Maršrutizavimo informacija, Šiuo atveju, ji nurodo Spring karkasui,
    // jog visas HTTP užklausas, kurių kelias yra "/" apdoros metodas "home".
    //galima daryti be, jeigu frontende ir backende kintamūjų pavadinimai nesiskiria
    @RequestMapping(method = RequestMethod.GET, value="/")
    String home(Model model) {
        // Jeigu Model 'number' nepraeina validacijos - per jį grąžinamos validacijos klaidos į View
        model.addAttribute("number", new Number());
        return "skaiciuotuvas";
    }
    //@PostMapping trumpesnis @RequestMapping(method = RequestMethod.POST, value="") variantas
    @PostMapping("/skaiciuoti")
//  String skaiciuoti(@RequestParam HashMap<String, String> skaiciai, ModelMap modelMap){
    String skaiciuoti(@Valid @ModelAttribute("number") Number e, BindingResult br,
                      @RequestParam HashMap<String, String> skaiciai, ModelMap modelMap) {

        if (br.hasErrors()) {
            return "skaiciuotuvas";
        } else {
            int sk1 = Integer.parseInt(skaiciai.get("sk1"));
            int sk2 = Integer.parseInt(skaiciai.get("sk2"));
            String zenklas = skaiciai.get("zenklas");
            System.out.println(skaiciai.entrySet());
            int rezultatas = 0;
            if (zenklas.equals("+")) {
                rezultatas = sk1 + sk2;
            }
            if (zenklas.equals("-")) {
                rezultatas = sk1 - sk2;
            }
            if (zenklas.equals("*")) {
                rezultatas = sk1 * sk2;
            }
            if (zenklas.equals("/")) {
                rezultatas = sk1 / sk2;
            }
            //ModelMap objektas naudojamas siųsti reikšmes iš kSpring MVC controller į JSP
            modelMap.put("sk1", sk1);
            modelMap.put("sk2", sk2);
            modelMap.put("zenklas", zenklas);
            modelMap.put("rezultatas", rezultatas);

            //Kreipiamės į service, kuris savo ruožtu kreipiasi į DAO ir išsaugo įrašą į DB
            numberService.save(new Number(sk1, sk2, zenklas, rezultatas));
            return "skaiciuoti";
        }
    }
}
