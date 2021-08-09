package dev.filipegomes.springboot2.requests;

import lombok.Data;

@Data
public class ProductPutRequestBody {
    private Long id;
    private String name;
}
