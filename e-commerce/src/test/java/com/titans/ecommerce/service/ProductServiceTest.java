package com.titans.ecommerce.service;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.dto.ProductVO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        productDTO2.setName("iPhone2");
        Product productDTO3 = new Product();
        productDTO3.setProductCategory("electronic");
        productDTO3.setName("iPhone3");
        Product productDTO4 = new Product();
        productDTO4.setProductCategory("electronic");
        productDTO4.setName("iPhone4");

        productRepository.save(productDTO1);
        productRepository.save(productDTO2);
        productRepository.save(productDTO3);
        productRepository.save(productDTO4);


    }

    @Test
    void test(){
        ((List<ProductVO>)(productService.getProducts(0, 10).get("data"))).forEach(System.out::println);
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


        printHashMap(productService.getProducts(0, 1));
        System.out.println("---");
        printHashMap(productService.getProducts(1, 1));
        System.out.println("---");
        printHashMap(productService.getProducts(2, 1));
        System.out.println("---");
        printHashMap(productService.getProducts(3, 1));
        System.out.println("---");
        printHashMap(productService.getProducts(0, 2));
        System.out.println("---");
        printHashMap(productService.getProducts(1, 2));
        System.out.println("---");
        printHashMap(productService.getProducts(0, 3));
        System.out.println("---");
        printHashMap(productService.getProducts(1, 3));


        System.out.println("----------");

        printHashMap(productService.searchProducts("iPhone", "electronic",0, 3));
        System.out.println("---");
        printHashMap(productService.searchProducts("", "electronic",0, 2));
        System.out.println("---");
        printHashMap(productService.searchProducts("", "electronic",1, 2));
        System.out.println("---");

    }

    void printHashMap(HashMap<String, Object> map){
        System.out.println("page: " + map.get("page"));
        System.out.println("size: " + map.get("size"));
        ((List<ProductVO>)map.get("data")).forEach(System.out::println);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }
}