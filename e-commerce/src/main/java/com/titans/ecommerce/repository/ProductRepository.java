package com.titans.ecommerce.repository;

import com.titans.ecommerce.entity.Product;
import com.titans.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
