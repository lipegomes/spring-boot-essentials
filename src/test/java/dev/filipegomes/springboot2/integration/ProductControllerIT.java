package dev.filipegomes.springboot2.integration;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.repository.ProductRepository;
import dev.filipegomes.springboot2.util.ProductCreator;
import dev.filipegomes.springboot2.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ProductControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private ProductRepository productRepository;
    @Test
    @DisplayName("List returns list of product inside page object when successful")
    void list_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {
        Product saveProduct = productRepository.save(ProductCreator.createProductToBeSaved());

        String expectedName = saveProduct.getName();

        PageableResponse<Product> productPage = testRestTemplate.exchange("/products", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Product>>() {
                }).getBody();

        Assertions.assertThat(productPage).isNotNull();

        Assertions.assertThat(productPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}
