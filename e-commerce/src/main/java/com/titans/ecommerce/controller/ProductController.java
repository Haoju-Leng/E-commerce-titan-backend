package com.titans.ecommerce.controller;

import com.titans.ecommerce.config.JwtService;
import com.titans.ecommerce.entity.Product;
import com.titans.ecommerce.entity.User;
import com.titans.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final JwtService jwtService;

    @GetMapping({"/", "/actuator/info"})
    ResponseEntity<String> info() {
        // Indicate the request succeeded and return the application
        // name.
        StringBuilder resp = new StringBuilder();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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




}
