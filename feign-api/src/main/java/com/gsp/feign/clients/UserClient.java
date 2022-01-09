package com.gsp.feign.clients;

import com.gsp.feign.clients.fallback.UserClientFallbackFactory;
import com.gsp.feign.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-service", fallbackFactory = UserClientFallbackFactory.class)
@RequestMapping("/user")
public interface UserClient {
    @GetMapping("/userName/{orderId}")
    String findByOrderId(@PathVariable("orderId") Long id);

    @GetMapping("/userDetail/{orderId}")
    User findDetailByOrderId(@PathVariable("orderId") Long id);
}
