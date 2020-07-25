package com.electromall.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountResponseDto {

    @Getter
    @Builder
    public static class Profile {
        private String name;
        private boolean agreeMessageByEmail;
        private String roadAddr;
        private String buildingName;
        private String detailAddr;
    }

}
