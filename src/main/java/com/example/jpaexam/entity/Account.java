package com.example.jpaexam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String passwordHash;
    private int role; // 1.admin | 2.user
    private int status; // 1.active | 0. deactive

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Credential> credentials;

    public String getRoleName() {
        return role == 1 ? "ADMIN" : "USER";
    }
}

