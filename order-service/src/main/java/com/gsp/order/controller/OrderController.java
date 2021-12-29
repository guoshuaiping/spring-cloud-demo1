package com.gsp.order.controller;

import com.gsp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String function () {
        return "hello order";
    }
    @GetMapping("/order/userName/{orderId}")
    public String queryUserNameByOrderId(@PathVariable("orderId") Long orderId) {
        return orderService.queryUserNameByOrderId(orderId);
    }
}
