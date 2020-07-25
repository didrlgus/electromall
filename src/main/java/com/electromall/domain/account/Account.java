package com.electromall.domain.account;

import com.electromall.domain.BaseTimeEntity;
import com.electromall.web.dto.AccountResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private LocalDate birth;

    @Column
    private String roadAddr;

    @Column
    private String buildingName;

    @Column
    private String detailAddr;

    @Column
    private boolean agreeMessageByEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Account(Long id, String name, String password, String email, LocalDate birth, Role role,
                String roadAddr, String buildingName, String detailAddr, boolean agreeMessageByEmail) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.role = role;
        this.roadAddr = roadAddr;
        this.buildingName = buildingName;
        this.detailAddr = detailAddr;
        this.agreeMessageByEmail = agreeMessageByEmail;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public AccountResponseDto.Profile toProfileResponseDto(Account account) {
        return AccountResponseDto.Profile.builder()
                .name(account.getName())
                .agreeMessageByEmail(account.isAgreeMessageByEmail())
                .roadAddr(account.getRoadAddr())
                .buildingName(account.getBuildingName())
                .detailAddr(account.getDetailAddr())
                .build();
    }
}
