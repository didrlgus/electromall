package com.electromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomeView() {

        return "index";
    }

    @GetMapping("/login")
    public String getLoginView() {

        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpView() {

        return "signup";
    }
}
