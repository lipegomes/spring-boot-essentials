package dev.filipegomes.springboot2.controller;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.service.ProductService;
import dev.filipegomes.springboot2.util.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Product> productPage = new PageImpl<>(List.of(ProductCreator.createValidProduct()));
        BDDMockito.when(productServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(productPage);
    }

    @Test
    @DisplayName("List returns list of product inside page object when successful")
    void list_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        Page<Product> productPage = productController.list(null).getBody();

        Assertions.assertThat(productPage).isNotNull();

        Assertions.assertThat(productPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}