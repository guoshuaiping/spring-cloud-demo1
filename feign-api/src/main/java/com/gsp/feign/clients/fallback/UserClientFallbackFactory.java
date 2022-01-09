package com.gsp.feign.clients.fallback;

import com.gsp.feign.clients.UserClient;
import com.gsp.feign.domain.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public String findByOrderId(Long id) {
               log.error("find by order id error", throwable);
               return "not find...";
            }

            @Override
            public User findDetailByOrderId(Long id) {
                log.error("find detail by order id error", throwable);
                return new User();
            }
        };
    }
}
