package dev.filipegomes.springboot2.controller;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.requests.ProductPostRequestBody;
import dev.filipegomes.springboot2.requests.ProductPutRequestBody;
import dev.filipegomes.springboot2.service.ProductService;
import dev.filipegomes.springboot2.util.ProductCreator;
import dev.filipegomes.springboot2.util.ProductPostRequestBodyCreator;
import dev.filipegomes.springboot2.util.ProductPutRequestBodyCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
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

        BDDMockito.when(productServiceMock.listAllNonPageable())
                .thenReturn(List.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(ProductCreator.createValidProduct());

        BDDMockito.when(productServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productServiceMock.save(ArgumentMatchers.any(ProductPostRequestBody.class)))
                .thenReturn(ProductCreator.createValidProduct());

        BDDMockito.doNothing().when(productServiceMock).replace(ArgumentMatchers.any(ProductPutRequestBody.class));
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

    @Test
    @DisplayName("ListAll returns list of product when successful")
    void listAll_ReturnsListOfProducts_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        List<Product> products = productController.listAll().getBody();

        Assertions.assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns product when successful")
    void findById_ReturnsProduct_WhenSuccessful() {
        Long expectedId = ProductCreator.createValidProduct().getId();

        Product product = productController.findById(1).getBody();

        Assertions.assertThat(product).isNotNull();

        Assertions.assertThat(product.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of product when successful")
    void findByName_ReturnsListOfProduct_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        List<Product> products = productController.findByName("product").getBody();

        Assertions.assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of product is not found")
    void findByName_ReturnsEmptyListOfProduct_WhenAnimeIsNotFound() {
        BDDMockito.when(productServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Product> products = productController.findByName("product").getBody();

        Assertions.assertThat(products)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns product when successful")
    void save_ReturnsProduct_WhenSuccessful() {
        Product product = productController.save(ProductPostRequestBodyCreator.createProductPostRequestBody()).getBody();

        Assertions.assertThat(product).isNotNull().isEqualTo(ProductCreator.createValidProduct());
    }

    @Test
    @DisplayName("replace updates product when successful")
    void replace_UpdatesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> productController.replace(ProductPutRequestBodyCreator.createProductPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Product> entity = productController.replace(ProductPutRequestBodyCreator.createProductPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    @DisplayName("delete removes product when successful")
    void delete_RemovesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> productController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Product> entity = productController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
