package com.titans.ecommerce.service;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    void setUp() {
        Product productDTO1 = new Product();
        productDTO1.setProductCategory("book");
        productDTO1.setName("childhood");
        Product productDTO2 = new Product();
        productDTO2.setProductCategory("electronic");
        productDTO2.setName("iPhone");

        productRepository.save(productDTO1);
        productRepository.save(productDTO2);


    }

    @Test
    void test(){
        productService.getProducts().forEach(System.out::println);

        System.out.println(productRepository.findByNameContainingIgnoreCaseAndProductCategory("child", "").size());
        System.out.println("Should be 1");
        System.out.println(productRepository.findByNameContainingIgnoreCaseAndProductCategory("child", "book").size());
        System.out.println("Should be 1");

        System.out.println(productRepository.findByNameContainingIgnoreCaseAndProductCategory("child", "bo").size());
        System.out.println("Should be 0");

        System.out.println(productRepository.findByNameContainingIgnoreCaseAndProductCategory("", "bo").size());
        System.out.println("Should be 0");

        System.out.println(productRepository.findByNameContainingIgnoreCaseAndProductCategory("", "book").size());
        System.out.println("Should be 1");





    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }
}