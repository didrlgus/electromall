package com.electromall.web;

import com.electromall.auth.AuthUtils;
import com.electromall.auth.WithAccount;
import com.electromall.domain.account.AccountRepository;
import com.electromall.domain.account.CustomUserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.electromall.utils.ApiUtils.API_VERSION;
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

    public void callGetProfileAPI(Long accountId, ResultMatcher status) throws Exception {

        mockMvc.perform(get(API_VERSION + "/account/" + accountId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status)
                .andReturn();
    }
}
