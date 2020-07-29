package com.electromall.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class AccountRequestDto {

    @Builder
    @Getter
    public static class Update {
        @NotBlank
        @Length(max = 20)
        private String name;
        @Length(max = 50)
        private String roadAddr;
        @Length(max = 50)
        private String buildingName;
        @Length(max = 50)
        private String detailAddr;
        private boolean agreeMessageByEmail;
    }

    @NoArgsConstructor
    @Getter
    public static class Password {
        @Length(min = 8, max = 50)
        private String password;

        @Builder
        public Password(String password) {
            this.password = password;
        }
    }
}
