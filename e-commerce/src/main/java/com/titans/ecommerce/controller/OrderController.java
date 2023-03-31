package com.titans.ecommerce.controller;

import com.titans.ecommerce.models.vo.OrderVO;
import com.titans.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getBuyingOrders")
    ResponseEntity<?> getBuyingOrders(){
        List<OrderVO> orderVOs = orderService.findBuyingOrdersByUser();
        if(orderVOs.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVOs);
        }
    }

    @GetMapping("/getSellingOrders")
    ResponseEntity<?> getSellingOrders(){
        List<OrderVO> orderVOs = orderService.findSellingOrdersByUser();
        if(orderVOs.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVOs);
        }
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam(name = "productId") Integer productId, @RequestParam(name = "deliveryMethod") String method){
        OrderVO orderVO = orderService.createOrder(productId, method);
        if(orderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVO);
        }
    }

    @PostMapping("/approve")
    ResponseEntity<?> approve(@RequestParam(name = "productId") Integer productId){
        OrderVO orderVO = orderService.approveOrderBySeller(productId);
        if(orderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVO);
        }
    }

    @PostMapping("/deny")
    ResponseEntity<?> deny(@RequestParam(name = "productId") Integer productId){
        OrderVO orderVO = orderService.denyOrderBySeller(productId);
        if(orderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVO);
        }
    }

    @PutMapping("/remove")
    ResponseEntity<?> remove(@RequestParam(name = "orderId") Integer orderId){
        try {
            orderService.deleteProcessingOrder(orderId);
            return ResponseEntity.ok("Order successfully deleted");
        } catch(Exception e) {
            return ResponseEntity.ok("Unable to delete order");
        }
    }

    @GetMapping("/getOrderInfo")
    ResponseEntity<?> getOrderInfoByProductId(@RequestParam(name = "productId") Integer productId){
        OrderVO orderVO = orderService.findOrderProductId(productId);
        if(orderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(orderVO);
        }
    }
}
