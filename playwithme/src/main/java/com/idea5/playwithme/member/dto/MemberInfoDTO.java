package com.idea5.playwithme.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private String ageRange;
    private String gender;
}
