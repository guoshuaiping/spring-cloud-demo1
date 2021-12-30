package com.gsp.user.controller;

import com.gsp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${pattern.dateformat}")
    private String dateformat;

    @GetMapping("/userName/{orderId}")
    public String getUserNameByOrderId(@PathVariable("orderId") Long orderId){
        return userService.getUserNameByOrderId(orderId);
    }

    @GetMapping("now")
    public String now(){
        System.out.println(dateformat);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat, Locale.CHINA));
    }
}
