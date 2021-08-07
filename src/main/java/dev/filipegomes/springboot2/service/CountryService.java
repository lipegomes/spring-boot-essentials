package dev.filipegomes.springboot2.service;

import dev.filipegomes.springboot2.domain.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    public List<Country> listAll() {
        return List.of(new Country(6, "Brazil"), new Country(94, "Tajikistan"), new Country(2, "India"));
    }
}
