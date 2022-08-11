package com.idea5.playwithme.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor @ToString
@Data
public class KakaoProfileDTO {
    private String email;
    private String ageRange;
    private String name;
    private String gender;
}
