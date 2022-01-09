package com.gsp.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gsp.feign.domain.User;
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
    // /order 可用于模拟限流配置之【直接模式】
    @RequestMapping("/order")
    public String function () {
        return "hello order";
    }

    @SentinelResource("hot") // 用于模拟【热点参数限流】，热点参数限流对默认的springMVC资源无效，所以需要使用该注解声明
    @GetMapping("/order/userName/{orderId}")
    public String queryUserNameByOrderId(@PathVariable("orderId") Long orderId) {
        return orderService.queryUserNameByOrderId(orderId);
    }
    @GetMapping("/order/userDetail/{orderId}")
    public User queryUserDetailByOrderId(@PathVariable("orderId") Long orderId) {
        return orderService.queryUserDetailByOrderId(orderId);
    }
    // /order/query 和 /order/update 可用于模拟限流高级配置之【关联模式流控】
    @GetMapping("/order/query")
    public String queryOrder() {
        return "订单查询成功~";
    }
    @GetMapping("/order/update")
    public String updateOrder() {
        return "更新订单成功！";
    }

    // /order/queryGoods 和 /order/updateGoods 和 orderService 可用于模拟限流高级配置之【链路模式流控】
    @GetMapping("/order/queryGoods")
    public String queryGoods() {
        return orderService.queryGoods();
    }
    @GetMapping("/order/updateGoods")
    public  String updateGoods() {
        String queryGoodsResult = orderService.queryGoods();
        return  queryGoodsResult + ",更新商品信息成功";
    }
}
