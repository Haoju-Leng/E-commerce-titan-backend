package com.titans.ecommerce.repository;

import com.titans.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
