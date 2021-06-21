package com.example.jpaexam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    @Id
    private String tokenKey;
    private String createdAt;
    private String expiredAt;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId") // khoa ngoai
    private Account account;
    @Column(insertable = false, updatable = false)
    private int userId;
}
