package com.idea5.playwithme.article.dto;

import com.idea5.playwithme.article.domain.Article;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {

    private Long id;
    private String title;
    private String contents;
    private Integer maxRecruitNum;
    private Boolean recruitStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String gender;
    private String ageRange;
    private Long boardId;
    private Long memberId;

    // Article -> ArticleDto 변환
    public static ArticleResponseDto toDto(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .contents(article.getContents())
                .maxRecruitNum(article.getMaxRecruitNum())
                .recruitStatus(article.getRecruitStatus())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .gender(article.getGender())
                .ageRange(article.getAgeRange())
                .boardId(article.getBoard().getId())
                .memberId(article.getMember().getId())
                .build();
    }
}
