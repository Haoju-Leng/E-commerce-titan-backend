package com.titans.ecommerce.repository;

import com.titans.ecommerce.models.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);

    List<Product> findAllBySellerId(Integer sellerId);


    List<Product> findByNameContainingIgnoreCaseAndProductCategory(String name, String productCategory);

    List<Product> findByNameContainingIgnoreCaseAndProductCategory(String name, String productCategory, Pageable pageable);


}
