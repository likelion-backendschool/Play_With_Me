package com.idea5.playwithme.article.dto;

import com.idea5.playwithme.article.domain.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ArticleDto {

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
    public static ArticleDto toDto(Article article) {
        return ArticleDto.builder()
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

    // ArticleDto -> Article 변환
    public static Article toEntity(ArticleDto articleDto) {
        return Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .contents(articleDto.getContents())
                .maxRecruitNum(articleDto.getMaxRecruitNum())
                .recruitStatus(articleDto.getRecruitStatus())
                .gender(articleDto.getGender())
                .ageRange(articleDto.getAgeRange())
                .build();
    }
}
