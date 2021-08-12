package dev.filipegomes.springboot2.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductPostRequestBody {
    @NotEmpty(message = "Product name cannot be empty")
    private String name;
}
