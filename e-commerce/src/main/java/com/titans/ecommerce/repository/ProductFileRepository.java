package com.titans.ecommerce.repository;

import com.titans.ecommerce.models.entity.Product;
import com.titans.ecommerce.models.entity.ProductFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductFileRepository extends JpaRepository<ProductFile, Integer> {

}
