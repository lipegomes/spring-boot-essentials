package dev.filipegomes.springboot2.repository;

import dev.filipegomes.springboot2.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
