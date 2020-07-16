package com.electromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/orderComplete")
    public String getOrderCompleteView() {

        return "order/order-completed";
    }
}
