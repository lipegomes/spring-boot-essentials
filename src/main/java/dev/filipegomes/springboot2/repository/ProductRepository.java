package dev.filipegomes.springboot2.repository;

import dev.filipegomes.springboot2.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> listAll();
}
