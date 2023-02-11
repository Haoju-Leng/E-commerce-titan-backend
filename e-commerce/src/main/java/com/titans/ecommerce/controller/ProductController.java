package com.titans.ecommerce.controller;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping( "/actuator/info")
    ResponseEntity<String> info() {
        // Indicate the request succeeded and return the application
        // name.
        StringBuilder resp = new StringBuilder();
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        resp
                .append("Hello User! Your info:")
                .append("\n")
                .append(user.toString())
                .append("\n")
                .append("Application is alive and running on ")
                .append(Thread.currentThread())
                .append("\n");

        return ResponseEntity
                .ok(resp.toString());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById (@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id);
        if (null != product) {
            logger.info("Product found: " + product);
            return ResponseEntity.ok(product);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts () {
        List<Product> productList = productService.getProducts();
        if (null != productList) {
            logger.info("Product found: " + productList);
            return ResponseEntity.ok(productList);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct (@RequestBody ProductDTO productDTO) {
        return ResponseEntity
                .ok(productService.addProduct(productDTO));
    }
}
