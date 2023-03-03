package com.titans.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.titans.ecommerce.models.dto.CartDTO;
import com.titans.ecommerce.models.vo.CartVO;
import com.titans.ecommerce.service.CartService;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    @GetMapping("")
    ResponseEntity<?> get(){
        CartVO cartVO=cartService.getCart();
        if(cartVO==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cartVO);
        }
    }

    @PostMapping("/add")
    ResponseEntity<?> add(@RequestBody CartDTO cartDTO){
        CartVO cartVO=cartService.addCart(cartDTO);
        if(cartVO==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cartVO);
        }
    }

    @PutMapping("/edit/{id}")
    ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody CartDTO cartDTO){
        CartVO cartVO=cartService.editCart(id, cartDTO);
        if(cartVO==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cartVO);
        }
    }
}
