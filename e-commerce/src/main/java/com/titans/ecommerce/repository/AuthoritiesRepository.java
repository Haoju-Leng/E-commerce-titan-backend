package com.titans.ecommerce.repository;

import com.titans.ecommerce.entity.Authorities;
import com.titans.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}
