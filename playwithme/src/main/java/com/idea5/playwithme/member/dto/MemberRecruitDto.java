package com.idea5.playwithme.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRecruitDto {

    private Long id;
    private String username; // 식별용 키
    private String nickname;


}
