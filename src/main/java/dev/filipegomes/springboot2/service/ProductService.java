package dev.filipegomes.springboot2.service;

import dev.filipegomes.springboot2.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ProductService {
    private static List<Product> products;

    static {
        products = new ArrayList<>(List.of(
                new Product(1L, "Calça Jeans"),
                new Product(2L, "Monitor UHD 4K 27 Polegadas"),
                new Product(3L, "Teclado Mecânico com Led")
        ));
    }
    public List<Product> listAll() {
        return products;
    }

    public Product findById(long id) {
        return products
                .stream()
                .filter(country -> country.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not Found"));
    }

    public Product save(Product product) {
        product.setId(ThreadLocalRandom.current().nextLong(1, 1_000));
        products.add(product);
        return product;
    }

    public void delete(long id) {
        products.remove(findById(id));
    }
}
