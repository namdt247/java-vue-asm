package com.example.jpaexam.controller;

import com.example.jpaexam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/login")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        String token = accountService.login(username, password);
        if (token.isEmpty() || token.length() == 0) {
            return "Token not found";
        }
        return token;
    }
}
