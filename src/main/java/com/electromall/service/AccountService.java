package com.electromall.service;

import com.electromall.domain.account.Account;
import com.electromall.domain.account.AccountRepository;
import com.electromall.domain.account.Role;
import com.electromall.domain.account.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean signUp(SignUpForm signUpForm) {
        // 이메일 유효성 검사
        if(accountRepository.existsByEmail(signUpForm.getEmail())) {
            return false;
        }
        saveAccount(signUpForm);
        return true;
    }

    public void saveAccount(SignUpForm signUpForm) {
        accountRepository.save(Account.builder()
                .name(signUpForm.getName())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .role(Role.USER)
                .build());
    }
}
