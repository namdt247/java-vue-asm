package com.example.jpaexam.controller;

import com.example.jpaexam.dto.AccountDTO;
import com.example.jpaexam.dto.CredentialDTO;
import com.example.jpaexam.dto.LoginDTO;
import com.example.jpaexam.entity.Account;
import com.example.jpaexam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public CredentialDTO login(@RequestBody LoginDTO loginDTO) {
        CredentialDTO credential = accountService.login(loginDTO);
        if (credential == null) {
            return null;
        }
        return credential;
    }

    @RequestMapping(value = "/api/info", method = RequestMethod.GET)
    public AccountDTO getInformation(@RequestHeader("Authorization") String token) {
        Account accountByToken = accountService.findByToken(token.replace("Bearer", "").trim());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountByToken.getId());
        accountDTO.setUserName(accountByToken.getUserName());
        accountDTO.setRole(accountByToken.getRole());
        accountDTO.setStatus(accountByToken.getStatus());
        return accountDTO;
    }
}
