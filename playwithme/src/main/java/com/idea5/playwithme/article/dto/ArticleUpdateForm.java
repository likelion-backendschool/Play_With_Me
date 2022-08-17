package com.idea5.playwithme.article.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateForm {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목을 200자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String contents;

    @NotNull(message = "최대 모집 인원을 선택해주세요.")
    private Integer maxRecruitNum;

    @NotEmpty(message = "모집 성별을 선택해주세요.")
    private String gender;

    @NotEmpty(message = "모집 나이대를 선택해주세요.")
    private String ageRange;
}
