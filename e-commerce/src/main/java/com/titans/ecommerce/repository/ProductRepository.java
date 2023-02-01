package com.titans.ecommerce.repository;

import com.titans.ecommerce.entity.Product;
import com.titans.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Product findProductById(Integer id);
}
