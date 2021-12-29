package com.gsp.user.controller;

import com.gsp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/userName/{orderId}")
    public String getUserNameByOrderId(@PathVariable("orderId") Long orderId){
        return userService.getUserNameByOrderId(orderId);
    }
}
