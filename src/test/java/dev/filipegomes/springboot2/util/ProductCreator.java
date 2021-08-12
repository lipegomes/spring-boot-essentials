package dev.filipegomes.springboot2.util;

import dev.filipegomes.springboot2.domain.Product;

public class ProductCreator {
    public static Product createProductToBeSaved() {
        return Product.builder()
                .name("Carrinho De Controle Remoto Cr Gw126 2,4ghz 4wd")
                .build();
    }

    public static Product createValidProduct() {
        return Product.builder()
                .name("Carrinho De Controle Remoto Cr Gw126 2,4ghz 4wd")
                .id(1L)
                .build();
    }

    public static Product createValidUpdatedProduct() {
        return Product.builder()
                .name("Carrinho De Controle Remoto Cr Gw126 2,4ghz 4wd")
                .id(1L)
                .build();
    }
}
