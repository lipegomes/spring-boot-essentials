package dev.filipegomes.springboot2.util;

import dev.filipegomes.springboot2.requests.ProductPutRequestBody;

public class ProductPutRequestBodyCreator {
    public static ProductPutRequestBody createProductPutRequestBody() {
        return ProductPutRequestBody.builder()
                .id(ProductCreator.createProductToBeSaved().getId())
                .name(ProductCreator.createProductToBeSaved().getName())
                .build();
    }
}
