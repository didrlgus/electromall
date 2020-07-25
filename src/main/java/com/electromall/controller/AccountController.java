package com.electromall.controller;

import com.electromall.domain.account.form.SignUpForm;
import com.electromall.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.electromall.utils.ExceptionUtils.DUPLICATED_EMAIL_MASSAGE;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/profile")
    public String getAccountView() {

        return "account/my-account";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpView(Model model) {
        model.addAttribute(new SignUpForm());
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute SignUpForm signUpForm,
                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            return "account/signup";
        }

        boolean result = accountService.signUp(signUpForm);

        if(!result) {
            model.addAttribute("error", DUPLICATED_EMAIL_MASSAGE);
            return "account/signup";
        }

        return "redirect:/";
    }

}
