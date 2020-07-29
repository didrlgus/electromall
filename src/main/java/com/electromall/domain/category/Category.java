package com.electromall.domain.category;

import com.electromall.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {

    @Id     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String catCd;

    @Column
    private String catNm;

    @Column
    private String upprCatCd;

    @Column
    private Integer catLv;

    @Column
    private String cnntUrl;

    @Builder
    public Category(Long id, String catCd, String catNm,
                    String upprCatCd, Integer catLv, String cnntUrl) {

        this.id = id;
        this.catCd = catCd;
        this.catNm = catNm;
        this.upprCatCd = upprCatCd;
        this.catLv = catLv;
        this.cnntUrl = cnntUrl;
    }

}
