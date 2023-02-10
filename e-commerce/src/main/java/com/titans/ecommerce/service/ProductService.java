package com.titans.ecommerce.service;

import com.titans.ecommerce.entity.Product;
import com.titans.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Integer id) {

        return productRepository.findProductById(id);
    }

}
