package com.example.jpaexam.controller;

import com.example.jpaexam.entity.Product;
import com.example.jpaexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Product> list() {
        return productService.list();
    }
}
