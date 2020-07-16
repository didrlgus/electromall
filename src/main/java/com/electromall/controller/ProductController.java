package com.electromall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String getProductsView() {

        return "product/product-list";
    }

    @GetMapping("/productDetail")
    public String getProductDetailView() {

        return "product/product-detail";
    }
}
