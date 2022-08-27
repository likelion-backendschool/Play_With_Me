package com.idea5.playwithme.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor @ToString
@Data
public class KakaoUser {
    private Long id;
    private String email;
    private String ageRange;
    private String nickname;
    private String gender;
}
