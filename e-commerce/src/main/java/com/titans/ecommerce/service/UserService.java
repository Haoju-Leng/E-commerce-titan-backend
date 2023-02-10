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
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        if (userRepository.findUserByUsername(username) != null) {
            return userRepository.findUserByUsername(username);
        }
        userRepository.save(user);
        return user;
    }

    public User login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (null != user && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }else {
            return null;
        }
    }
}
