package dev.filipegomes.springboot2.repository;

import dev.filipegomes.springboot2.domain.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> listAll();
}
