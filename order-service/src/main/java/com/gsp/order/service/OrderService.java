package com.gsp.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    public String queryUserNameByOrderId(Long orderId) {
        //此时还无法使用服务名进行访问，回报错：java.net.UnknownHostException: user-service
        //String url = "http://user-service/userName/"+orderId;
        // 可以使用如下直接访问服务提供方ip+端口方式直接访问，但有弊端，后续需要改进成通过服务名方式
        String url = "http://localhost:8081/userName/"+orderId;
        String userName = restTemplate.getForObject(url,String.class);
        return userName;
    }
}
