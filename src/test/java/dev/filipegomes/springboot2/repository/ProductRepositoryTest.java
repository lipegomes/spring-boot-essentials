package dev.filipegomes.springboot2.repository;

import dev.filipegomes.springboot2.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for product repository")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Saves creates anime when Successful")
    void save_PersistProduct_WhenSuccessful() {
        Product productToBeSaved = createProduct();
        Product productSaved = this.productRepository.save(productToBeSaved);
        Assertions.assertThat(productSaved).isNotNull();
        Assertions.assertThat(productSaved.getName()).isEqualTo(productToBeSaved.getName());
    }

    private Product createProduct() {
        return Product.builder()
                .name("Carrinho De Controle Remoto Cr Gw126 2,4ghz 4wd")
                .build();
    }
}
