package dev.filipegomes.springboot2.controller;

import dev.filipegomes.springboot2.domain.Country;
import dev.filipegomes.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("country")
@Log4j2
@RequiredArgsConstructor
public class CountryController {
    private final DateUtil dateUtil;

    @GetMapping(path = "list")
    //localhost:8080/country/list
    public List<Country> list(){
        log.info(dateUtil.formatLocalDateTimeToDataBasStyle(LocalDateTime.now()));
        return List.of(new Country("Brazil"), new Country("Nigeria") , new Country("India"));
    }

    //
    @GetMapping(path = "list2")
    public List<Country> list2(){
        log.info(dateUtil.formatLocalDateTimeToDataBasStyle(LocalDateTime.now()));
        return List.of(new Country("Armenia"), new Country("Denmark"), new Country("Thailand"));
    }
}
