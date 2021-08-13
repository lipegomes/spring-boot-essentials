package dev.filipegomes.springboot2.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPutRequestBody {
    private Long id;
    private String name;
}
