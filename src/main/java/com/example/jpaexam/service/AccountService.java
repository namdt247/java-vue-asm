package com.example.jpaexam.service;

import com.example.jpaexam.dto.CredentialDTO;
import com.example.jpaexam.dto.LoginDTO;
import com.example.jpaexam.entity.Account;
import com.example.jpaexam.entity.Credential;
import com.example.jpaexam.repository.AccountRepository;
import com.example.jpaexam.repository.CredentialRepository;
import com.example.jpaexam.util.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Configurable
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialRepository credentialRepository;

    public Account findByToken(String accessToken) {
        Optional<Credential> credentialOptional = credentialRepository.findById(accessToken);
        if (credentialOptional.isPresent()) {
            Credential credential = credentialOptional.get();
            if (credential.isExpired()) {
                return null;
            }
            Account account = accountRepository.findById(credential.getUserId()).orElse(null);
            return account;
        }
        return null;
    }
    public Account create(Account account) {
        String password = account.getPasswordHash();
        account.setPasswordHash(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    public CredentialDTO login(LoginDTO loginDTO) {
        Optional<Account> accountOptional = accountRepository.findAccountByUserName(loginDTO.getUserName());
        if (!accountOptional.isPresent()) {
            return null;
        }
        Account account = accountOptional.get();
        if (!passwordEncoder.matches(loginDTO.getPassword(), account.getPasswordHash())) {
            return null;
        }
        Credential credential = new Credential();
        credential.setTokenKey(UUID.randomUUID().toString());
        credential.setAccount(account);
        credential.setCreatedAt(new Date());
        credential.setExpiredAt(TimeHelper.addDaysToCurrentTime(7)); // add 7 days
        Credential saved = credentialRepository.save(credential);
        return new CredentialDTO(saved);
    }
}
