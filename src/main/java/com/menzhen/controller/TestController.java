package com.menzhen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("index")
    public  String test(){
        System.out.println("test123");
        return "admin/index";
    }

}
