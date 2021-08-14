package dev.filipegomes.springboot2.service;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.exception.BadRequestException;
import dev.filipegomes.springboot2.repository.ProductRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Product> productPage = new PageImpl<>(List.of(ProductCreator.createValidProduct()));
        BDDMockito.when(productRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(productPage);

        BDDMockito.when(productRepositoryMock.findAll())
                .thenReturn(List.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreator.createValidProduct());

        BDDMockito.doNothing().when(productRepositoryMock).delete(ArgumentMatchers.any(Product.class));
    }

    @Test
    @DisplayName("ListAll returns list of product inside page object when successful")
    void listAll_ReturnsListOfProductsInsidePageObject_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        Page<Product> productPage = productService.listAll(PageRequest.of(1, 1));

        Assertions.assertThat(productPage).isNotNull();

        Assertions.assertThat(productPage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(productPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("ListAll returns list of product when successful")
    void listAll_ReturnsListOfProducts_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        List<Product> products = productService.listAllNonPageable();

        Assertions.assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns product when successful")
    void findByIdOrThrowBadRequestException_ReturnsProduct_WhenSuccessful() {
        Long expectedId = ProductCreator.createValidProduct().getId();

        Product product = productService.findByIdOrThrowBadRequestException(1);

        Assertions.assertThat(product).isNotNull();

        Assertions.assertThat(product.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException  throws BadRequestException when anime is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenProductIsNotFound() {
        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> productService.findByIdOrThrowBadRequestException(1));
    }

    @Test
    @DisplayName("findByName returns a list of product when successful")
    void findByName_ReturnsListOfProduct_WhenSuccessful() {
        String expectedName = ProductCreator.createValidProduct().getName();

        List<Product> products = productService.findByName("product");

        Assertions.assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of product is not found")
    void findByName_ReturnsEmptyListOfProduct_WhenAnimeIsNotFound() {
        BDDMockito.when(productRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Product> products = productService.findByName("product");

        Assertions.assertThat(products)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns product when successful")
    void save_ReturnsProduct_WhenSuccessful() {
        Product product = productService.save(ProductPostRequestBodyCreator.createProductPostRequestBody());

        Assertions.assertThat(product).isNotNull().isEqualTo(ProductCreator.createValidProduct());
    }

    @Test
    @DisplayName("delete removes product when successful")
    void delete_RemovesProduct_WhenSuccessful() {
        Assertions.assertThatCode(() -> productService.delete(1))
                .doesNotThrowAnyException();
    }
}
