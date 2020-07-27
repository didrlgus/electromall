package com.electromall.web;

import com.electromall.auth.AuthUtils;
import com.electromall.auth.WithAccount;
import com.electromall.domain.account.AccountRepository;
import com.electromall.exception.ErrorResponse;
import com.electromall.web.dto.AccountRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.electromall.utils.ApiUtils.API_VERSION;
import static com.electromall.utils.ExceptionUtils.NO_EXIST_USER_MESSAGE;
import static com.electromall.utils.RequestSuccessUtils.UPDATE_PASSWORD_SUCCESS_MESSAGE;
import static com.electromall.utils.RequestSuccessUtils.UPDATE_PROFILE_SUCCESS_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountApiControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthUtils authUtils;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void after() {
        accountRepository.deleteAll();
    }

    // 회원 프로필 조회 테스트
    @WithAccount("test@naver.com")
    @Test
    public void getProfileTest() throws Exception {
        Long accountId = authUtils.getAuthId();

        callGetProfileAPI(accountId, status().isOk());
    }

    // 회원 프로필 조회 권한 테스트
    @Test
    public void getProfileAuthTest() throws Exception {
        Long anonymousId = 0L;

        callGetProfileAPI(anonymousId, status().is3xxRedirection());
    }

    // 회원 프로필 조회 유효성 테스트
    @WithAccount("test@naver.com")
    @Test
    public void getProfileValidTest() throws Exception {
        Long accountId = authUtils.getAuthId() + 100L;

        MvcResult result = callGetProfileAPI(accountId, status().is4xxClientError());

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);

        assertEquals(errorResponse.getMessage(), NO_EXIST_USER_MESSAGE);
    }

    // 회원 프로필 수정 테스트
    @WithAccount("test@naver.com")
    @Test
    public void updateProfileTest() throws Exception {
        Long accountId = authUtils.getAuthId();

        AccountRequestDto.Update requestDto = AccountRequestDto.Update.builder()
                .name("update-test")
                .agreeMessageByEmail(true)
                .roadAddr("update-test")
                .buildingName("update-test")
                .detailAddr("update-test")
                .build();

        MvcResult result = callUpdateProfileAPI(accountId, requestDto, status().isOk());

        assertEquals(result.getResponse().getContentAsString(), UPDATE_PROFILE_SUCCESS_MESSAGE);
    }

    // 회원 프로필 수정 유효성 테스트
    @WithAccount("test@naver.com")
    @Test
    public void updateProfileValidTest() throws Exception {
        Long accountId = authUtils.getAuthId();

        AccountRequestDto.Update requestDto = AccountRequestDto.Update.builder()
                .name("")
                .agreeMessageByEmail(false)
                .roadAddr("update-test")
                .buildingName("update-test")
                .detailAddr("update-test")
                .build();

        callUpdateProfileAPI(accountId, requestDto, status().is4xxClientError());
    }

    // 회원 비밀번호 수정 테스트
    @WithAccount("test@naver.com")
    @Test
    public void updatePasswordValidTest() throws Exception {
        Long accountId = authUtils.getAuthId();

        AccountRequestDto.Password requestDto = AccountRequestDto.Password.builder()
                .password("test-password")
                .build();

        MvcResult result = callUpdatePasswordAPI(accountId, requestDto, status().isOk());

        assertEquals(result.getResponse().getContentAsString(), UPDATE_PASSWORD_SUCCESS_MESSAGE);
    }

    public MvcResult callGetProfileAPI(Long accountId, ResultMatcher status) throws Exception {

        return mockMvc.perform(get(API_VERSION + "/account/" + accountId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status)
                .andReturn();
    }

    public MvcResult callUpdateProfileAPI(Long accountId, AccountRequestDto.Update requestDto,
                                          ResultMatcher status) throws Exception {

        return mockMvc.perform(put(API_VERSION + "/account/" + accountId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status)
                .andReturn();
    }

    public MvcResult callUpdatePasswordAPI(Long accountId, AccountRequestDto.Password requestDto,
                                           ResultMatcher status) throws Exception {

        return mockMvc.perform(put(API_VERSION + "/account/" + accountId + "/password")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status)
                .andReturn();
    }
}
