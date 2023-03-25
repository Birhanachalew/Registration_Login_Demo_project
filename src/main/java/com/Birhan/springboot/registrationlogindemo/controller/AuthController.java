package com.Birhan.springboot.registrationlogindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    //handler method to handle home page rquest
    @GetMapping("/index")
    public String home(){
        return "index";
    }
}
