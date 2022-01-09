package com.gsp.order.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gsp.feign.clients.UserClient;
import com.gsp.feign.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient; // 注入Feign客户端

    public String queryUserNameByOrderId(Long orderId) {
        // ①可以使用如下直接访问服务提供方ip+端口方式直接访问，但有弊端，后续需要改进成通过服务名方式
        // String url = "http://localhost:8081/userName/"+orderId;

        // ②给RestTemplate添加@LoadBalanced注解后，就可以使用服务提供者的名称进行远程调用了
        // String url = "http://user-service/userName/"+orderId;
        // String userName = restTemplate.getForObject(url,String.class);

        // ③使用Feign客户端代替原先的RestTemplate,更优雅
        String userName = userClient.findByOrderId(orderId);
        return userName;
    }

    public User queryUserDetailByOrderId(Long orderId) {
        User user = userClient.findDetailByOrderId(orderId);
        return user;
    }

    @SentinelResource("goods") // Sentinel默认只标记Controller中的方法为资源，如果要标记其他方法，需要利用@SentinelResource注解
    public String queryGoods() {
        System.err.println("查询商品");
        return "查询商品成功！";
    }
}
