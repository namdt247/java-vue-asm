package com.example.jpaexam.dto;

import com.example.jpaexam.entity.Credential;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CredentialDTO {
    private String tokenKey;
    private Date createdAt;
    private boolean isExpired;

    public CredentialDTO(Credential credential) {
        this.tokenKey = credential.getTokenKey();
        this.createdAt = credential.getCreatedAt();
        this.isExpired = credential.isExpired();
    }
}
