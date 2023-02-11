package com.titans.ecommerce.service;

import com.titans.ecommerce.models.dto.ProductDTO;
import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.User;
import com.titans.ecommerce.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Integer id) {
        return productRepository.findProductById(id);
    }

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setProductName(product.getProductName());
        product.setSellerId(getUser().getId());
        product.setState(Product.State.forSale);
        product.setCreateTime(new Date());
        return productRepository.save(product);
    }

    private User getUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
