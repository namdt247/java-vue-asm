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

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/generate")
public class GenerateController {
    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public Boolean generate() {
        List<Product> productList = Arrays.asList(
            new Product("Laptop HP Start", 1200),
            new Product("Laptop HP Black", 1200),
            new Product("Laptop Dell G4", 12300),
            new Product("Laptop Dell M2", 1200),
            new Product("Laptop Dell Note", 1200),
            new Product("Macbook Pro 2021", 1200),
            new Product("Macbook M1 2021", 1200),
            new Product("Macbook Air M1 2021", 1200),
            new Product("Laptop Dell Latitude", 1200),
            new Product("Laptop SamSung", 1200)
        );

        productService.create(productList);

        String password = "123456";

        Account account1 = new Account();
        account1.setUserName("admin");
        account1.setPasswordHash(password);
        account1.setRole(1);
        account1.setStatus(1);

        Account account2 = new Account();
        account2.setUserName("admin01");
        account2.setPasswordHash(password);
        account2.setRole(1);
        account2.setStatus(0);

        Account account3 = new Account();
        account3.setUserName("user");
        account3.setPasswordHash(password);
        account3.setRole(2);
        account3.setStatus(0);

        Account account4 = new Account();
        account4.setUserName("user01");
        account4.setPasswordHash(password);
        account4.setRole(2);
        account4.setStatus(1);

        accountService.create(account1);
        accountService.create(account2);
        accountService.create(account3);
        accountService.create(account4);
        return true;
    }
}
