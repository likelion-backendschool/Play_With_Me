package com.idea5.playwithme.article.dto;

import com.idea5.playwithme.article.domain.Article;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateForm {
    @NotEmpty(message = "제목을 입력해주세요.")
    @Size(max = 200, message = "제목을 200자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String contents;

    @NotNull(message = "최대 모집 인원을 선택해주세요.")
    private String maxRecruitNum;                   // TODO: maxRecruitNum, minAge, maxAge Integer에서 변경

    @NotEmpty(message = "모집 성별을 선택해주세요.")
    private String gender;

    @NotEmpty(message = "최소 모집 연령을 선택해주세요.")
    private String minAge;

    @NotEmpty(message = "최대 모집 연령을 선택해주세요.")
    private String maxAge;

    // ArticleDto -> Article 변환
    public static Article toEntity(ArticleCreateForm articleCreateForm) {
        // default: 모집 상태, 작성 일시, 수정 일시, 조회수
        return Article.builder()
                .title(articleCreateForm.getTitle())
                .contents(articleCreateForm.getContents())
                .maxRecruitNum(Integer.parseInt(articleCreateForm.getMaxRecruitNum()))
                .recruitStatus(true)    // default: 모집중
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .gender(articleCreateForm.getGender())
                .ageRange(articleCreateForm.getMinAge() + "~" + articleCreateForm.getMaxAge())
                .views(0)   // default
                .build();
    }
}
