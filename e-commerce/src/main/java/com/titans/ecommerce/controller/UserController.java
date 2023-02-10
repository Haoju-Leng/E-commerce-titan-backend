package com.titans.ecommerce.controller;

import com.titans.ecommerce.entity.User;
import com.titans.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/customer")
public class UserController {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserService userService;

    @GetMapping({"/", "/actuator/info"})
    ResponseEntity<String> info() {
        return ResponseEntity
                .ok(applicationContext.getId()
                        + " is alive\n");
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    ResponseEntity<User> register(@RequestParam String username, @RequestParam String password) {
//        return ResponseEntity
//                .ok(userService
//                        .register(username, password));
//    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
//        return ResponseEntity
//                .ok(userService
//                        .login(username, password));
//    }

}
