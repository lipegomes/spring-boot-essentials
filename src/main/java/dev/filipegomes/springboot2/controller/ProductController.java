package dev.filipegomes.springboot2.controller;

import dev.filipegomes.springboot2.domain.Product;
import dev.filipegomes.springboot2.service.ProductService;
import dev.filipegomes.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("products")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
    private final DateUtil dateUtil;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        log.info(dateUtil.formatLocalDateTimeToDataBasStyle(LocalDateTime.now()));
        return new ResponseEntity<>(productService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id)
    {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
}
