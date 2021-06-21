package com.example.jpaexam.controller;

import com.example.jpaexam.entity.Account;
import com.example.jpaexam.entity.Product;
import com.example.jpaexam.service.AccountService;
import com.example.jpaexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/generate")
public class GenerateController {
    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public Boolean generate() {
        Product product1 = new Product();
        product1.setName("Laptop HP");
        product1.setPrice(1200);

        Product product2 = new Product();
        product2.setName("Laptop Dell");
        product2.setPrice(1300);

        Product product3 = new Product();
        product3.setName("Laptop Dell 02");
        product3.setPrice(1350);

        Product product4 = new Product();
        product4.setName("Laptop Dell 03");
        product4.setPrice(1400);

        Product product5 = new Product();
        product5.setName("Macbook Pro 2021");
        product5.setPrice(1300);

        Product product6 = new Product();
        product6.setName("Macbook M1 2021");
        product6.setPrice(1200);

        Product product7 = new Product();
        product7.setName("Macbook Air M1 2021");
        product7.setPrice(1000);

        Product product8 = new Product();
        product8.setName("Laptop Asus");
        product8.setPrice(1300);

        Product product9 = new Product();
        product9.setName("Laptop Dell Latitude");
        product9.setPrice(900);

        Product product10 = new Product();
        product10.setName("Laptop SamSung");
        product10.setPrice(1300);

        productService.create(product1);
        productService.create(product2);
        productService.create(product3);
        productService.create(product4);
        productService.create(product5);
        productService.create(product6);
        productService.create(product7);
        productService.create(product8);
        productService.create(product9);
        productService.create(product10);

        String encoded = new BCryptPasswordEncoder().encode("123456");

        Account account1 = new Account();
        account1.setUserName("admin");
        account1.setPasswordHash(encoded);
        account1.setRole(1);
        account1.setStatus(1);

        Account account2 = new Account();
        account2.setUserName("admin01");
        account2.setPasswordHash(encoded);
        account2.setRole(1);
        account2.setStatus(0);

        Account account3 = new Account();
        account3.setUserName("user");
        account3.setPasswordHash(encoded);
        account3.setRole(2);
        account3.setStatus(0);

        Account account4 = new Account();
        account4.setUserName("user01");
        account4.setPasswordHash(encoded);
        account4.setRole(2);
        account4.setStatus(1);

        accountService.create(account1);
        accountService.create(account2);
        accountService.create(account3);
        accountService.create(account4);
        return true;
    }
}
