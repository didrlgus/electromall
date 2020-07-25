package com.electromall.auth;

import com.electromall.domain.account.form.SignUpForm;
import com.electromall.service.AccountService;
import com.electromall.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withAccount) {
        String email = withAccount.value();

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(email);
        signUpForm.setPassword("12345678");
        signUpForm.setName("test");

        accountService.saveAccount(signUpForm);

        // 인증 처리
        UserDetails principal = customUserDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
