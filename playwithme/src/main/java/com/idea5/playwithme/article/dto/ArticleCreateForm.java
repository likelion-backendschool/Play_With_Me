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
    private Integer maxRecruitNum;

    @NotEmpty(message = "모집 성별을 선택해주세요.")
    private String gender;

    @NotEmpty(message = "모집 나이대를 선택해주세요.")
    private String ageRange;

    // ArticleDto -> Article 변환
    public static Article toEntity(ArticleCreateForm articleCreateForm) {
        // default: 모집 상태, 작성 일시, 수정 일시
        return Article.builder()
                .title(articleCreateForm.getTitle())
                .contents(articleCreateForm.getContents())
                .maxRecruitNum(articleCreateForm.getMaxRecruitNum())
                .recruitStatus(true)    // default: 모집중
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .gender(articleCreateForm.getGender())
                .ageRange(articleCreateForm.getAgeRange())
                .build();
    }
}
