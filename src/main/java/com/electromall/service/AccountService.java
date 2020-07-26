package com.electromall.service;

import com.electromall.domain.account.Account;
import com.electromall.domain.account.AccountRepository;
import com.electromall.domain.account.Role;
import com.electromall.domain.account.form.SignUpForm;
import com.electromall.exception.ValidCustomException;
import com.electromall.utils.ExceptionUtils;
import com.electromall.web.dto.AccountRequestDto;
import com.electromall.web.dto.AccountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.NoSuchElementException;

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

    public AccountResponseDto.Profile getProfile(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ValidCustomException(ExceptionUtils.NO_EXIST_USER_MESSAGE));

        return account.toProfileResponseDto(account);
    }

    public void updateProfile(Long id, AccountRequestDto.Update requestDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ValidCustomException(ExceptionUtils.NO_EXIST_USER_MESSAGE));

        accountRepository.save(account.update(requestDto));
    }
}
