package com.electromall.web;

import com.electromall.service.AccountService;
import com.electromall.web.dto.AccountRequestDto;
import com.electromall.web.dto.AccountResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.electromall.utils.ApiUtils.API_VERSION;
import static com.electromall.utils.RequestSuccessUtils.UPDATE_PASSWORD_SUCCESS_MESSAGE;
import static com.electromall.utils.RequestSuccessUtils.UPDATE_PROFILE_SUCCESS_MESSAGE;

@RequiredArgsConstructor
@RequestMapping(API_VERSION)
@RestController
public class AccountApiController {

    private final AccountService accountService;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountResponseDto.Profile> getProfile(@PathVariable("id") Long id) {

        return ResponseEntity.ok(accountService.getProfile(id));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable("id") Long id,
                                           @Valid @RequestBody AccountRequestDto.Update requestDto) {

        accountService.updateProfile(id, requestDto);

        return ResponseEntity.ok(UPDATE_PROFILE_SUCCESS_MESSAGE);
    }

    @PutMapping("/account/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable("id") Long id,
                                            @Valid @RequestBody AccountRequestDto.Password requestDto) {

        accountService.updatePassword(id, requestDto);

        return ResponseEntity.ok(UPDATE_PASSWORD_SUCCESS_MESSAGE);
    }

}
