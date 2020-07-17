package com.electromall;


import com.electromall.exception.ValidCustomException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

@SpringBootApplication
public class ElectromallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectromallApplication.class, args);
    }

    // Exception의 타입을 체크하여 ValidCustomException일 경우에는 errors 필드를 추가하는 코드
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
                Throwable error = getError(webRequest);
                if (error instanceof ValidCustomException) {
                    errorAttributes.put("errors", ((ValidCustomException)error).getErrors());
                }
                return errorAttributes;
            }
        };
    }
}
