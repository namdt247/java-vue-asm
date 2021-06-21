package com.example.jpaexam.service;

import com.example.jpaexam.entity.Account;
import com.example.jpaexam.entity.Credential;
import com.example.jpaexam.repository.AccountRepository;
import com.example.jpaexam.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@Configurable
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private CredentialRepository credentialRepository;

    public Boolean create(Account account) {
        try {
            accountRepository.save(account);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUserName(username);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            String encoded = new BCryptPasswordEncoder().encode(password);
            if (encoded != account.getPasswordHash()) {
                return "Wrong password";
            }
            String token = UUID.randomUUID().toString();
            Credential credential = new Credential();
            credential.setAccount(account);
            credential.setTokenKey(token);
            credential.setUserId(account.getId());
            credentialRepository.save(credential);
            return token;
        }
        return "";
    }
}
