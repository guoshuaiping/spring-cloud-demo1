package com.gsp.user.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserNameByOrderId(Long orderId) {
       switch(orderId.toString()) {
           case "1" :

               return "aaa";
           case "2" :
               return "bbb";
           default:
               return "xxxx";
       }
    }
}
