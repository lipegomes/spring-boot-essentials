package dev.filipegomes.springboot2.service;

import dev.filipegomes.springboot2.domain.Country;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CountryService {
    private List<Country> countries = List.of(new Country(6, "Brazil"), new Country(94, "Tajikistan"), new Country(2, "India"));

    public List<Country> listAll() {
        return countries;
    }

    public Country findById(Integer id) {
        return countries
                .stream()
                .filter(country -> country.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Country not Found"));
    }
}
