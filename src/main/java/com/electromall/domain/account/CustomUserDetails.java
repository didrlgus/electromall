package com.electromall.domain.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String email;
    private String name;
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long id, String email, String name, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name=name;
        this.password=password;
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.authorities=authorities;
    }

    public CustomUserDetails(Long id, String email, String name, String password, Boolean accountNonExpired,
                             Boolean accountNonLocked, boolean credentialsNonExpired,
                             Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
