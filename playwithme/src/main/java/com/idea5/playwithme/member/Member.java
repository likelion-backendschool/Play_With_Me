package com.idea5.playwithme.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    private String ageRange;

    private String name;        // 닉네임이 될 것 같음

    private Integer mannerTemp;

    private String gender;

    @Builder
    public Member(String email, String ageRange, String name, Integer mannerTemp, String gender) {
        this.email = email;
        this.ageRange = ageRange;
        this.name = name;
        this.mannerTemp = mannerTemp;
        this.gender = gender;
    }
}
