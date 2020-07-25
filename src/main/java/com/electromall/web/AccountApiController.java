package com.electromall.web;

import com.electromall.service.AccountService;
import com.electromall.web.dto.AccountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.electromall.utils.ApiUtils.API_VERSION;

@RequiredArgsConstructor
@RequestMapping(API_VERSION)
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountResponseDto.Profile> getProfile(@PathVariable("id") Long id) {

        return ResponseEntity.ok(accountService.getProfile(id));
    }

}
