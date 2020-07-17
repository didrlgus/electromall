package com.electromall.service;

import com.electromall.domain.account.Account;
import com.electromall.domain.account.AccountRepository;
import com.electromall.exception.ValidCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.electromall.utils.ExceptionUtils.AUTHENTICATION_EXCEPTION_MESSAGE;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    // 사용자가 입력한 email(아이디)을 통해 디비에 저장된 유저 객체를 가져와서 UserDetails 객체로 변환하여 돌려주는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOpt = accountRepository.findByEmail(username);
        Account account = accountOpt.orElseThrow(() -> new ValidCustomException(AUTHENTICATION_EXCEPTION_MESSAGE, "email"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRoleKey()));

        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
