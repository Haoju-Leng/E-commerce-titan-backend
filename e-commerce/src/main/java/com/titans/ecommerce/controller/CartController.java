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
    ResponseEntity<?> add(@RequestParam(name = "productId") Integer productId){
        CartVO cartVO=cartService.add(productId);
        if(cartVO==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cartVO);
        }
    }

    @PutMapping("/remove")
    ResponseEntity<?> edit(@RequestParam(name = "productId") Integer productId){
        CartVO cartVO=cartService.edit(productId);
        if(cartVO==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cartVO);
        }
    }
}
