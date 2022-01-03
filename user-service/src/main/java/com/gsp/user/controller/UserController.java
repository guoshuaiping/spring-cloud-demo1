package com.gsp.user.controller;

import com.gsp.user.domain.User;
import com.gsp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${pattern.dateformat}")
    private String dateformat;
    @Value("${hotUpdate.myName}")
    private String myName;

    @GetMapping("/userName/{orderId}")
    public String getUserNameByOrderId(@PathVariable("orderId") Long orderId, @RequestHeader(value = "Truth", required = false) String truth, @RequestHeader(value = "Yes", required = false) String yes){
        System.out.println("truth: " + truth);
        System.out.println("yes: " + yes);
        return userService.getUserNameByOrderId(orderId);
    }

    @GetMapping("/userDetail/{orderId}")
    public User findDetailByOrderId(@PathVariable("orderId") Long orderId) {
        return userService.getDetailByOrderId(orderId);
    }

    @GetMapping("now")
    public String now(){
        System.out.println(dateformat);
        System.out.println("热更新：myName="+myName);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat, Locale.CHINA));
    }
}
