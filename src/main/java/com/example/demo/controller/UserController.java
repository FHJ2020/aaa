package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fanhuijie
 * @date 2021/2/23
 * @desc
 */
@Slf4j
@Controller
public class UserController {

    @GetMapping("/user")
    @ResponseBody
    public String getUser(){
        log.info("UserController#getUser ");
        return "user";
    }


    @GetMapping("/users")
    @ResponseBody
    public String listUsers(){
        if (1==1){
            throw new RuntimeException("aaaaaaaa");
        }
        return "users";
    }


}
