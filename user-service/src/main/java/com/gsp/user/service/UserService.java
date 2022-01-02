package com.gsp.user.service;

import com.gsp.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserNameByOrderId(Long orderId) {
       switch(orderId.toString()) {
           case "1" :

               return "aaa-8085";
           case "2" :
               return "bbb-8085";
           default:
               return "xxxx-8085";
       }
    }

    public User getDetailByOrderId(Long orderId) {
        System.out.println("order-detail-8085");
        return new User(888L,"gsp","shanxi");
    }
}
