package dev.filipegomes.springboot2.client;

import dev.filipegomes.springboot2.domain.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
                new ParameterizedTypeReference<>() {});

        log.info(exchange.getBody());

        // Product car = Product.builder().name("Volvo S90").build();
        // Product carSaved =  new RestTemplate().postForObject("http://localhost:8080/products/", car, Product.class);
        // log.info("saved car {}", carSaved);

        Product bike = Product.builder().name("Mountain bike Sutton").build();
        ResponseEntity<Product> bikeSaved = new RestTemplate().exchange("http://localhost:8080/products/",
                HttpMethod.POST,
                new HttpEntity<>(bike, createJsonHeader()),
                Product.class);

        log.info("saved product {} ", bikeSaved);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
