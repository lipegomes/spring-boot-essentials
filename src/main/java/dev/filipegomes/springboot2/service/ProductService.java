package dev.filipegomes.springboot2.service;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.repository.ProductRepository;
import dev.filipegomes.springboot2.requests.ProductPostRequestBody;
import dev.filipegomes.springboot2.requests.ProductPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product findByIdOrThrowBadRequestException(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not Found"));
    }

    public Product save(ProductPostRequestBody productPostRequestBody) {
        Product product = Product
                .builder()
                .name(productPostRequestBody.getName())
                .build();
        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ProductPutRequestBody productPutRequestBody) {
        Product savedProduct = findByIdOrThrowBadRequestException(productPutRequestBody.getId());
        Product product = Product.builder()
                .id(savedProduct.getId())
                .name(productPutRequestBody.getName())
                .build();

        productRepository.save(product);
    }
}
