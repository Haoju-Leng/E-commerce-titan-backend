package com.titans.ecommerce.repository;

import com.titans.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    Optional<User> findByEmail(String email);

    void deleteUserById(Integer id);

}
