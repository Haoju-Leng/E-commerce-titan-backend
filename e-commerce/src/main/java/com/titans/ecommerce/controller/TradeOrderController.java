package com.titans.ecommerce.controller;

import com.titans.ecommerce.models.vo.TradeOrderVO;
import com.titans.ecommerce.service.TradeOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/order")
public class TradeOrderController {
    private final Logger logger = LoggerFactory.getLogger(TradeOrderController.class);

    private final TradeOrderService tradeOrderService;

    public TradeOrderController(TradeOrderService tradeOrderService) {
        this.tradeOrderService = tradeOrderService;
    }

    @GetMapping("/getBuyingOrders")
    ResponseEntity<?> getBuyingOrders(){
        List<TradeOrderVO> tradeOrderVOS = tradeOrderService.findBuyingOrdersByUser();
        if(tradeOrderVOS.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVOS);
        }
    }

    @GetMapping("/getSellingOrders")
    ResponseEntity<?> getSellingOrders(){
        List<TradeOrderVO> tradeOrderVOS = tradeOrderService.findSellingOrdersByUser();
        if(tradeOrderVOS.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVOS);
        }
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam(name = "productId") Integer productId, @RequestParam(name = "deliveryMethod") String method){
        TradeOrderVO tradeOrderVO = tradeOrderService.createOrder(productId, method);
        if(tradeOrderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVO);
        }
    }

    @PostMapping("/approve")
    ResponseEntity<?> approve(@RequestParam(name = "productId") Integer productId){
        TradeOrderVO tradeOrderVO = tradeOrderService.approveOrderBySeller(productId);
        if(tradeOrderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVO);
        }
    }

    @PostMapping("/deny")
    ResponseEntity<?> deny(@RequestParam(name = "productId") Integer productId){
        TradeOrderVO tradeOrderVO = tradeOrderService.denyOrderBySeller(productId);
        if(tradeOrderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVO);
        }
    }

    @PutMapping("/remove")
    ResponseEntity<?> remove(@RequestParam(name = "orderId") Integer orderId){
        try {
            tradeOrderService.deleteProcessingOrder(orderId);
            return ResponseEntity.ok("Order successfully deleted");
        } catch(Exception e) {
            return ResponseEntity.ok("Unable to delete order");
        }
    }

    @GetMapping("/getOrderInfo")
    ResponseEntity<?> getOrderInfoByProductId(@RequestParam(name = "productId") Integer productId){
        TradeOrderVO tradeOrderVO = tradeOrderService.findOrderProductId(productId);
        if(tradeOrderVO == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tradeOrderVO);
        }
    }
}
