package dev.filipegomes.springboot2.util;

import dev.filipegomes.springboot2.requests.ProductPostRequestBody;

public class ProductPostRequestBodyCreator {
    public static ProductPostRequestBody createProductPostRequestBody() {
        return ProductPostRequestBody.builder()
                .name(ProductCreator.createProductToBeSaved().getName())
                .build();
    }
}
