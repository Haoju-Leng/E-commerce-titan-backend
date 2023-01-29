package com.titans.ecommerce.service;

import com.titans.ecommerce.entity.User;
import com.titans.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User register(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password));
        if (userRepository.findById(username).isPresent()) {
            return userRepository.findById(username).get();
        }
        userRepository.save(user);
        return user;
    }

    public User login(String username, String password) {
        User user = userRepository.findById(username).orElse(null);
        if (null != user && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }else {
            return null;
        }
    }
}
