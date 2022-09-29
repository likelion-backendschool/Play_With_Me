package com.idea5.playwithme.member.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDTO {

    private Long memberId;
    private String nickname;
    private String ageRange;
    private String email;
    private String mannerTemp;
    private String gender;
    private String createDate;
}
