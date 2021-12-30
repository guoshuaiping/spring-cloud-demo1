package com.gsp.user.controller;

import com.gsp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RefreshScope
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${pattern.dateformat}")
    private String dateformat;
    @Value("${hotUpdate.myName}")
    private String myName;

    @GetMapping("/userName/{orderId}")
    public String getUserNameByOrderId(@PathVariable("orderId") Long orderId){
        return userService.getUserNameByOrderId(orderId);
    }

    @GetMapping("now")
    public String now(){
        System.out.println(dateformat);
        System.out.println("热更新：myName="+myName);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat, Locale.CHINA));
    }
}
