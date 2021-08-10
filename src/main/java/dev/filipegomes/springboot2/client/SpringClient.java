package dev.filipegomes.springboot2.client;

import dev.filipegomes.springboot2.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Product> entity = new RestTemplate().getForEntity("http://localhost:8080/products/{id}", Product.class, 3);
        log.info(entity);

        Product object = new RestTemplate().getForObject("http://localhost:8080/products/{id}", Product.class, 3);

        log.info(object);

        Product[] products = new RestTemplate().getForObject("http://localhost:8080/products/all", Product[].class);

        log.info(Arrays.toString(products));

        ResponseEntity<List<Product>> exchange =  new RestTemplate().exchange("http://localhost:8080/products/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Product>>() {});

        log.info(exchange.getBody());
    }
}
