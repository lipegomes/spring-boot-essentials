package dev.filipegomes.springboot2.repository;

import dev.filipegomes.springboot2.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
