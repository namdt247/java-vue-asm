package com.example.jpaexam.config;

import com.example.jpaexam.entity.Account;
import com.example.jpaexam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUserName(userName);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            UserDetails userDetails =
                    User.builder()
                        .username(account.getUserName())
                        .password(account.getPasswordHash())
                        .roles(account.getRole() == 1 ? "Admin" : "User")
                        .build();
            return userDetails;
        }


        return null;
    }
}
