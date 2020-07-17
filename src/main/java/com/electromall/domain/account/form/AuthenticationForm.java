package com.electromall.domain.account.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticationForm {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
