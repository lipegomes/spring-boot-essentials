package dev.filipegomes.springboot2.mapper;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.requests.ProductPostRequestBody;
import dev.filipegomes.springboot2.requests.ProductPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract Product toProduct(ProductPostRequestBody productPostRequestBody);

    public abstract Product toProduct(ProductPutRequestBody productPutRequestBody);
}
