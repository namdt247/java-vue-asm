package com.example.jpaexam.dto;

import com.example.jpaexam.entity.Account;

public class AccountDTO {
    private int id;
    private String userName;
    private int role;
    private int status;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userName = account.getUserName();
        this.role = account.getRole();
        this.status = account.getStatus();
    }
}
