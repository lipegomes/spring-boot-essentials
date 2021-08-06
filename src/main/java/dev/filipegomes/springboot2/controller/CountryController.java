package dev.filipegomes.springboot2.controller;

import dev.filipegomes.springboot2.domain.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("country")
public class CountryController {
    @GetMapping(path = "list")
    public List<Country> list(){
        return List.of(new Country("Brazil"), new Country("Nigeria") , new Country("India"));
    }
}
