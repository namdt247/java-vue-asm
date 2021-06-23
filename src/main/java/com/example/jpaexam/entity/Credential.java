package com.example.jpaexam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    @Id
    private String tokenKey;
    private Date createdAt;
    private Date expiredAt;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Account account;
    @Column(insertable = false, updatable = false)
    private int userId;

    public boolean isExpired() {
        return new Date().getTime() > expiredAt.getTime();
    }
}
